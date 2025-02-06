import org.example.PrimeChecker;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;

public class ParallelThreadsUnitTest {
    @Test
    void exampleArrayTest() throws InterruptedException, ExecutionException {
        int[] mas1 = new int[]{6, 8, 7, 13, 5, 9, 4};
        int[] mas2 = new int[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        for (int i = 1; i <= 8; i++) {
            assertTrue(PrimeChecker.hasNonPrimeParallelThreads(mas1, i));
            assertFalse(PrimeChecker.hasNonPrimeParallelThreads(mas2, i));
        }
    }

    @Test
    void negativeArrayTest() throws InterruptedException, ExecutionException {
        int [] mas = new int[1000000];
        for (int i = 1; i <= 1000000; i++) {
            mas[i-1] = -i;
        }
        assertTrue(PrimeChecker.hasNonPrimeParallelThreads(mas, 4));
    }

    @Test
    void billionArrayTest1() throws InterruptedException, ExecutionException {
        int [] mas = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            mas[i] = i;
        }
        assertTrue(PrimeChecker.hasNonPrimeParallelThreads(mas, 1));
    }

    @Test
    void billionArrayTest2() throws InterruptedException, ExecutionException {
        int [] mas = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            mas[i] = i;
        }
        assertTrue(PrimeChecker.hasNonPrimeParallelThreads(mas, 2));
    }

    @Test
    void billionArrayTest3() throws InterruptedException, ExecutionException {
        int [] mas = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            mas[i] = i;
        }
        assertTrue(PrimeChecker.hasNonPrimeParallelThreads(mas, 3));
    }

    @Test
    void billionArrayTest4() throws InterruptedException, ExecutionException {
        int [] mas = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            mas[i] = i;
        }
        assertTrue(PrimeChecker.hasNonPrimeParallelThreads(mas, 4));
    }
}