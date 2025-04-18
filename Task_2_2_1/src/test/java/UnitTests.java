import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pizzeria.workers.*;
import pizzeria.model.*;

class PizazzStoreTest {
    @Test
    void testBakersAndCouriersWorkCorrectly() throws InterruptedException {
        OrderQueue orderQueue = new OrderQueue();
        Storage store = new Storage(10);

        Baker baker1 = new Baker("Nikita", 1, orderQueue, store);
        Baker baker2 = new Baker("Nikita2", 2, orderQueue, store);
        Courier courier1 = new Courier("Nikita Krainov", 3, store);
        Courier courier2 = new Courier("Ochir Khubanov", 2, store);

        baker1.start();
        baker2.start();
        courier1.start();
        courier2.start();

        for (int i = 1; i <= 5; i++) {
            orderQueue.addOrder(new Order(i));
            Thread.sleep(1000);
        }

        baker1.interrupt();
        baker2.interrupt();
        courier1.interrupt();
        courier2.interrupt();

        assertEquals(0, store.getStorage().size());
    }
}