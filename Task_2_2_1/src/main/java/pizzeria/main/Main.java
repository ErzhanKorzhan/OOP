package pizzeria.main;

import pizzeria.config.ConfigReader;
import pizzeria.model.Pizzeria;
import static pizzeria.logger.LoggingComponent.logger;

public class Main {
    public static void main(String[] args) {
        try {
            ConfigReader configReader = new ConfigReader("config.json");
            Pizzeria pizzeria = new Pizzeria(configReader);
            pizzeria.start();
        } catch (Exception e) {
            logger.warn("Ошибка при инициализации конфига: {}", e.getMessage());
        }
    }
}