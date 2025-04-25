import org.junit.jupiter.api.*;
import pizzeria.config.ConfigReader;
import pizzeria.model.Pizzeria;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

class PizzeriaSystemTest {
    private static final String CONFIG_PATH = "config.json";
    private static final String LOG_PATH = "logs/pizzeria.log";
    private static String originalConfig;

    @BeforeAll
    static void backupConfig() throws Exception {
        // Сохраняем оригинальный конфиг
        originalConfig = Files.readString(Paths.get(CONFIG_PATH));
    }

    @AfterAll
    static void restoreConfig() throws Exception {
        // Восстанавливаем конфиг после всех тестов
        Files.write(Paths.get(CONFIG_PATH), originalConfig.getBytes());
    }

    @Test
    void fullOrderLifecycleTest() throws Exception {
        // 1. Подготовка тестового конфига
        String testConfig = """
        {
            "storageCapacity": 5,
            "amt": 3,
            "workTime": 10000,
            "bakers": [{"name": "TestBaker", "speed": 1000}],
            "couriers": [{"name": "TestCourier", "speed": 500, "capacity": 3}]
        }
        """;
        Files.write(Paths.get(CONFIG_PATH), testConfig.getBytes());

        Path path = Paths.get(LOG_PATH);

        // 2. Очистка логов
        Files.deleteIfExists(path);

        // 3. Запуск пиццерии
        ConfigReader configReader = new ConfigReader(CONFIG_PATH);
        try {
            Pizzeria pizzeria = new Pizzeria(configReader);
            pizzeria.start();
        } catch (Exception e) {
            fail("Ошибка запуска пиццерии", e);
        }

        // 4. Ожидание завершения работы пиццерии
        Thread.sleep(configReader.getWorkTime() + 2000);

        // 5. Проверка логов
        String logs = Files.readString(path);
        verifyLogs(logs);
    }

    private void verifyLogs(String logs) {
        for (int orderId = 1; orderId <= 3; orderId++) {
            String cookedPattern = "Пекарь TestBaker приготовил заказ №" + orderId;
            String deliveredPattern = "Курьер TestCourier доставил заказ №" + orderId;

            assertTrue(logs.contains(cookedPattern),
                    "Не найдено приготовление заказа " + orderId);
            assertTrue(logs.contains(deliveredPattern),
                    "Не найдена доставка заказа " + orderId);
        }
    }
}