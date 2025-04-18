package pizzeria.workers;

import pizzeria.model.Order;
import pizzeria.model.OrderQueue;
import pizzeria.model.Storage;

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
                System.out.println("Заказ №" + order.getId() + " готовится пекарем " + name);
                Thread.sleep(speed);
                storage.storePizza(order);
            }
        } catch (InterruptedException e) {
            System.out.println("Пекарь " + name + " завершает работу");
        }
    }
}