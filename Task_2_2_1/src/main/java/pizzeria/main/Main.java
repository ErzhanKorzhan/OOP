package pizzeria.main;

import pizzeria.model.*;
import pizzeria.workers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode config = mapper.readTree(new File("config.json"));

        int bakersCount = config.get("bakers").size();
        int couriersCount = config.get("couriers").size();
        int storageCapacity = config.get("storageCapacity").asInt();

        OrderQueue orderQueue = new OrderQueue();
        Storage storage = new Storage(storageCapacity);

        List<Baker> bakers = new LinkedList<>();
        List<Courier> couriers = new LinkedList<>();

        for (int i = 0; i < bakersCount; i++) {
            String name = config.get("bakers").get(i).get("name").asText();
            int speed = config.get("bakers").get(i).get("speed").asInt();
            bakers.add(new Baker(name, speed, orderQueue, storage));
        }
        for (int i = 0; i < couriersCount; i++) {
            String name = config.get("couriers").get(i).get("name").asText();
            int capacity = config.get("couriers").get(i).get("capacity").asInt();
            couriers.add(new Courier(name, capacity, storage));
        }

        bakers.forEach(Thread::start);
        couriers.forEach(Thread::start);
        int amtOrders = config.get("amt").asInt();

        for (int i = 1; i <= amtOrders; i++) {
            orderQueue.addOrder(new Order(i));
            Thread.sleep(1000);
        }

        bakers.forEach(Thread::interrupt);
        couriers.forEach(Thread::interrupt);
    }
}