import parallel.checkers.ParallelFuture;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;

class ParallelFutureUnitTest {
    ParallelFuture parallelThreadChecker1 = new ParallelFuture(1);
    ParallelFuture parallelThreadChecker2 = new ParallelFuture(2);
    ParallelFuture parallelThreadChecker3 = new ParallelFuture(3);
    ParallelFuture parallelThreadChecker4 = new ParallelFuture(4);
    ParallelFuture parallelThreadChecker6 = new ParallelFuture(6);
    ParallelFuture parallelThreadChecker8 = new ParallelFuture(8);
    ParallelFuture parallelThreadChecker10 = new ParallelFuture(10);

    @Test
    void exampleArrayTest() throws ExecutionException, InterruptedException {
        int[] mas1 = new int[]{6, 8, 7, 13, 5, 9, 4};
        int[] mas2 = new int[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertTrue(parallelThreadChecker4.hasNonPrime(mas1));
        assertFalse(parallelThreadChecker4.hasNonPrime(mas2));
    }

    @Test
    void negativeArrayTest() throws ExecutionException, InterruptedException {
        int [] mas = new int[1000000];
        Arrays.fill(mas, -1);
        assertTrue(parallelThreadChecker4.hasNonPrime(mas));
    }

    @Test
    void billionArrayTest1() throws ExecutionException, InterruptedException {
        int [] mas = new int[100000000];
        Arrays.fill(mas, 3);
        assertFalse(parallelThreadChecker1.hasNonPrime(mas));
    }

    @Test
    void billionArrayTest2() throws ExecutionException, InterruptedException {
        int [] mas = new int[100000000];
        Arrays.fill(mas, 3);
        assertFalse(parallelThreadChecker2.hasNonPrime(mas));
    }

    @Test
    void billionArrayTest3() throws ExecutionException, InterruptedException {
        int [] mas = new int[100000000];
        Arrays.fill(mas, 3);
        assertFalse(parallelThreadChecker3.hasNonPrime(mas));
    }

    @Test
    void billionArrayTest4() throws ExecutionException, InterruptedException {
        int [] mas = new int[100000000];
        Arrays.fill(mas, 3);
        assertFalse(parallelThreadChecker4.hasNonPrime(mas));
    }

    @Test
    void billionArrayTest6() throws ExecutionException, InterruptedException {
        int [] mas = new int[100000000];
        Arrays.fill(mas, 3);
        assertFalse(parallelThreadChecker6.hasNonPrime(mas));
    }

    @Test
    void billionArrayTest8() throws ExecutionException, InterruptedException {
        int [] mas = new int[100000000];
        Arrays.fill(mas, 3);
        assertFalse(parallelThreadChecker8.hasNonPrime(mas));
    }

    @Test
    void billionArrayTest10() throws ExecutionException, InterruptedException {
        int [] mas = new int[100000000];
        Arrays.fill(mas, 3);
        assertFalse(parallelThreadChecker10.hasNonPrime(mas));
    }
}
