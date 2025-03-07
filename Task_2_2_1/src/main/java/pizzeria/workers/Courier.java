package pizzeria.workers;

import pizzeria.model.Order;
import pizzeria.model.Storage;

import java.util.List;

public class Courier extends Thread {
    private final int id;
    private final int capacity;
    private final Storage storage;

    public Courier(int id, int capacity, Storage storage) {
        this.id = id;
        this.capacity = capacity;
        this.storage = storage;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                List<Order> orders = storage.takePizzas(capacity);
                System.out.println("Курьер " + id + " доставляет заказы " + orders);
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            System.out.println("Курьер " + id + " завершает работу");
        }
    }
}