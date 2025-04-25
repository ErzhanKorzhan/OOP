package pizzeria.workers;

import pizzeria.model.Order;
import pizzeria.model.Storage;
import java.util.List;
import static pizzeria.logger.LoggingComponent.logger;
import static pizzeria.model.Pizzeria.remainingOrders;

public class Courier extends Thread {
    private final String name;
    private final int capacity;
    private final Storage storage;
    private final int speed;

    public Courier(String name, int capacity, Storage storage, int speed) {
        this.name = name;
        this.capacity = capacity;
        this.storage = storage;
        this.speed = speed;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                List<Order> orders = storage.takePizzas(capacity);
                while (!orders.isEmpty() && !Thread.currentThread().isInterrupted()) {
                    Thread.sleep(speed);
                    Order order = orders.remove(0);
                    logger.info("Курьер {} доставил заказ №{}", name, order.getId());
                    remainingOrders.decrementAndGet();
                }
            }
        } catch (InterruptedException e) {
            logger.info("Курьер {} завершил работу", name);
            Thread.currentThread().interrupt();
        }
    }
}