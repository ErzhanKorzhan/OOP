package pizzeria.model;

import pizzeria.config.ConfigReader;
import pizzeria.workers.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import static pizzeria.logger.LoggingComponent.logger;

public class Pizzeria {
    private final OrderQueue orderQueue = new OrderQueue();
    private final Storage storage;
    private final List<Baker> bakers;
    private final List<Courier> couriers;
    private final ScheduledExecutorService timer;
    private final ConfigReader configReader;
    private int totalOrders;
    public static final AtomicInteger remainingOrders = new AtomicInteger(0);
    private Thread orderGenerationThread;

    public Pizzeria(ConfigReader configReader) {
        this.configReader = configReader;
        this.storage = new Storage(configReader.getStorageCapacity());
        this.bakers = createBakers();
        this.couriers = createCouriers();
        this.timer = Executors.newSingleThreadScheduledExecutor();
    }

    private List<Baker> createBakers() {
        List<Baker> bakers = new ArrayList<>();
        for (ConfigReader.BakerConfig cfg : configReader.getBakers()) {
            bakers.add(new Baker(cfg.name, cfg.speed, orderQueue, storage));
        }
        return bakers;
    }

    private List<Courier> createCouriers() {
        List<Courier> couriers = new ArrayList<>();
        for (ConfigReader.CourierConfig cfg : configReader.getCouriers()) {
            couriers.add(new Courier(cfg.name, cfg.capacity, storage, cfg.speed));
        }
        return couriers;
    }

    public void start() {
        scheduleShutdown();
        initializeWorkers();
        generateOrders();
    }

    private void initializeWorkers() {
        bakers.forEach(Thread::start);
        couriers.forEach(Thread::start);
    }

    private void scheduleShutdown() {
        int workTime = configReader.getWorkTime();
        logger.info("    -----ПИЦЦЕРИЯ ОТКРЫТА-----");
        timer.schedule(this::shutdown, workTime, TimeUnit.MILLISECONDS);
    }

    private void generateOrders() {
        totalOrders = configReader.getOrderCount();
        remainingOrders.set(totalOrders);

        orderGenerationThread = new Thread(() -> {
            try {
                generateOrdersLoop();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        orderGenerationThread.start();
    }

    private void generateOrdersLoop() throws InterruptedException {
        for (int i = 0; i < totalOrders; i++) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            orderQueue.addOrder(new Order(i + 1));
            Thread.sleep(1000);
        }
    }

    private void shutdown() {
        orderGenerationThread.interrupt();
        stopWorkers();
        updateConfig();
        timer.shutdown();
    }

    private void updateConfig() {
        try {
            int unfinishedOrders = remainingOrders.get();
            configReader.updateOrderCount(unfinishedOrders);
            logger.info("Осталось невыполненных заказов: {}", unfinishedOrders);
            logger.info("-----ПИЦЦЕРИЯ ЗАКРЫТА-----");
        } catch (IOException e) {
            logger.error("Ошибка при записи в конфиг: {}", e.getMessage());
        }
    }

    private void stopWorkers() {
        bakers.forEach(Thread::interrupt);
        couriers.forEach(Thread::interrupt);
    }
}