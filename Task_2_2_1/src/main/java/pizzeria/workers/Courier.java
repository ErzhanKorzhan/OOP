package pizzeria.workers;

import pizzeria.model.Order;
import pizzeria.model.Storage;
import java.util.List;

public class Courier extends Thread {
    private final String name;
    private final int capacity;
    private final Storage storage;

    public Courier(String name, int capacity, Storage storage) {
        this.name = name;
        this.capacity = capacity;
        this.storage = storage;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                List<Order> orders = storage.takePizzas(capacity);
                for (Order order : orders) {
                    System.out.println("Курьер " + name + " доставляет заказ " + order.getId());
                    Thread.sleep(300);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Курьер " + name + " завершает работу");
        }
    }
}