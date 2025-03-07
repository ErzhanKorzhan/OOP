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
        int workTime = config.get("workTime").asInt();

        OrderQueue orderQueue = new OrderQueue();
        Storage storage = new Storage(storageCapacity);

        List<Baker> bakers = new LinkedList<>();
        List<Courier> couriers = new LinkedList<>();

        for (int i = 0; i < bakersCount; i++) {
            int speed = config.get("bakers").get(i).get("speed").asInt();
            bakers.add(new Baker(i, speed, orderQueue, storage));
        }
        for (int i = 0; i < couriersCount; i++) {
            int capacity = config.get("couriers").get(i).get("capacity").asInt();
            couriers.add(new Courier(i, capacity, storage));
        }

        bakers.forEach(Thread::start);
        couriers.forEach(Thread::start);

        for (int i = 1; i <= 20; i++) {
            orderQueue.addOrder(new Order(i));
            Thread.sleep(1000);
        }

        Thread.sleep(workTime);
        bakers.forEach(Thread::interrupt);
        couriers.forEach(Thread::interrupt);
    }
}