import parallel.checkers.Sequential;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class SequentialUnitTest {
    Sequential sequentialChecker = new Sequential();

    @Test
    void exampleArrayTest() {
        int[] mas1 = new int[]{6, 8, 7, 13, 5, 9, 4};
        int[] mas2 = new int[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertTrue(sequentialChecker.hasNonPrime(mas1));
        assertFalse(sequentialChecker.hasNonPrime(mas2));
    }

    @Test
    void negativeArrayTest() {
        int [] mas = new int[1000000];
        Arrays.fill(mas, -1);
        assertTrue(sequentialChecker.hasNonPrime(mas));
    }

    @Test
    void billionArrayTest() {
        int [] mas = new int[100000000];
        Arrays.fill(mas, 3);
        assertFalse(sequentialChecker.hasNonPrime(mas));
    }
}
