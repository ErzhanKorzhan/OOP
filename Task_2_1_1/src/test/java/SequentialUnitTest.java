import org.example.PrimeChecker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SequentialUnitTest
{
    @Test
    void exampleArrayTest() {
        int[] mas1 = new int[]{6, 8, 7, 13, 5, 9, 4};
        int[] mas2 = new int[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertTrue(PrimeChecker.hasNonPrimeSequential(mas1));
        assertFalse(PrimeChecker.hasNonPrimeSequential(mas2));
    }

    @Test
    void negativeArrayTest()  {
        int [] mas = new int[1000000];
        for (int i = 1; i <= 1000000; i++) {
            mas[i-1] = -i;
        }
        assertTrue(PrimeChecker.hasNonPrimeSequential(mas));
    }

    @Test
    void billionArrayTest() {
        int [] mas = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            mas[i] = i;
        }
        assertTrue(PrimeChecker.hasNonPrimeSequential(mas));
    }
}