package pizzeria.workers;

import pizzeria.model.Order;
import pizzeria.model.OrderQueue;
import pizzeria.model.Storage;
import static pizzeria.logger.LoggingComponent.logger;

public class Baker extends Thread {
    private final String name;
    private final int speed;
    private final OrderQueue orderQueue;
    private final Storage storage;

    public Baker(String name, int speed, OrderQueue orderQueue, Storage storage) {
        this.name = name;
        this.speed = speed;
        this.orderQueue = orderQueue;
        this.storage = storage;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Order order = orderQueue.takeOrder();
                Thread.sleep(speed);
                logger.info("Пекарь {} приготовил заказ №{}", name, order.getId());
                storage.storePizza(order);
            }
        } catch (InterruptedException e) {
            logger.info("Пекарь {} завершил работу", name);
        }
    }
}