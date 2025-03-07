package pizzeria.model;

import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private final Queue<Order> queue = new LinkedList<>();

    public synchronized void addOrder(Order order) {
        queue.add(order);
        notifyAll();
    }

    public synchronized Order takeOrder() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.poll();
    }
}