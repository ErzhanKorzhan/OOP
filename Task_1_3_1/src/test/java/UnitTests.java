import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.Random;
import java.util.Arrays;

public class UnitTests
{
    Random rnd = new Random();

    @Test
    void OneElem()
    {
        int len = 1;
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i)
        {
            arr[i] = rnd.nextInt();
        }
        int[] out = arr.clone();
        Arrays.sort(arr);
        assertArrayEquals(arr, out);
    }
}