import org.example.ParallelThread;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ParallelThreadUnitTest {
    ParallelThread parallelThreadChecker1 = new ParallelThread(4);
    ParallelThread parallelThreadChecker2 = new ParallelThread(4);
    ParallelThread parallelThreadChecker3 = new ParallelThread(4);
    ParallelThread parallelThreadChecker4 = new ParallelThread(4);
    ParallelThread parallelThreadChecker6 = new ParallelThread(6);
    ParallelThread parallelThreadChecker8 = new ParallelThread(8);
    ParallelThread parallelThreadChecker10 = new ParallelThread(10);

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
