package pizzeria.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigReader {
    private final String configPath;
    private final PizzeriaConfig config;

    public ConfigReader(String configPath) throws IOException {
        this.configPath = configPath;
        ObjectMapper mapper = new ObjectMapper();
        this.config = mapper.readValue(new File(configPath), PizzeriaConfig.class);
    }

    public void updateOrderCount(int newAmt) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        config.amt = newAmt;
        mapper.writeValue(new File(configPath), config);
    }

    public int getStorageCapacity() {
        return config.storageCapacity;
    }

    public int getOrderCount() {
        return config.amt;
    }

    public int getWorkTime() {
        return config.workTime;
    }

    public List<BakerConfig> getBakers() {
        return config.bakers;
    }

    public List<CourierConfig> getCouriers() {
        return config.couriers;
    }

    public static class PizzeriaConfig {
        public int storageCapacity;
        public int amt;
        public int workTime;
        public List<BakerConfig> bakers;
        public List<CourierConfig> couriers;
    }

    public static class BakerConfig {
        public String name;
        public int speed;
    }

    public static class CourierConfig {
        public String name;
        public int speed;
        public int capacity;
    }
}