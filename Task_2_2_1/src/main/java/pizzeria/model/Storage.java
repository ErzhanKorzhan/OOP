package pizzeria.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Storage {
    private final Queue<Order> storage = new LinkedList<>();
    private final int capacity;

    public Storage(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void storePizza(Order order) throws InterruptedException {
        while (storage.size() >= capacity) {
            wait();
        }
        storage.add(order);
        System.out.println("[" + order.getId() + "] Пицца на складе");
        notifyAll();
    }

    public synchronized List<Order> takePizzas(int max) throws InterruptedException {
        while (storage.isEmpty()) {
            wait();
        }
        List<Order> taken = new LinkedList<>();
        for (int i = 0; i < max && !storage.isEmpty(); i++) {
            taken.add(storage.poll());
        }
        notifyAll();
        return taken;
    }
}
