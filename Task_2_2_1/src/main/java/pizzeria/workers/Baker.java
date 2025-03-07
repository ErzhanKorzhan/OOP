package pizzeria.workers;

import pizzeria.model.Order;
import pizzeria.model.OrderQueue;
import pizzeria.model.Storage;

public class Baker extends Thread {
    private final int id;
    private final int speed;
    private final OrderQueue orderQueue;
    private final Storage storage;

    public Baker(int id, int speed, OrderQueue orderQueue, Storage storage) {
        this.id = id;
        this.speed = speed;
        this.orderQueue = orderQueue;
        this.storage = storage;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Order order = orderQueue.takeOrder();
                System.out.println("[" + order.getId() + "] Готовится пекарем " + id);
                Thread.sleep(speed);
                storage.storePizza(order);
            }
        } catch (InterruptedException e) {
            System.out.println("Пекарь " + id + " завершает работу");
        }
    }
}