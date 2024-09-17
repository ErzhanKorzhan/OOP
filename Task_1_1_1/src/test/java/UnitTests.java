import org.example.HeapSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.Arrays;
import java.util.Random;

public class UnitTests
{
    Random rnd = new Random();
    HeapSort func = new HeapSort();

    @Test
    void TenThousElem()
    {
        int len = 10000;
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i)
        {
            arr[i] = rnd.nextInt();
        }
        int[] out = func.sort(arr, arr.length);
        Arrays.sort(arr);
        assertArrayEquals(arr, out);
    }

    @Test
    void HunThousElem()
    {
        int len = 100000;
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i)
        {
            arr[i] = rnd.nextInt();
        }
        int[] out = func.sort(arr, arr.length);
        Arrays.sort(arr);
        assertArrayEquals(arr, out);
    }

    @Test
    void MillionElem()
    {
        int len = 1000000;
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i)
        {
            arr[i] = rnd.nextInt();
        }
        int[] out = func.sort(arr, arr.length);
        Arrays.sort(arr);
        assertArrayEquals(arr, out);
    }

    @Test
    void TenMillionElem()
    {
        int len = 10000000;
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i)
        {
            arr[i] = rnd.nextInt();
        }
        int[] out = func.sort(arr, arr.length);
        Arrays.sort(arr);
        assertArrayEquals(arr, out);
    }

    @Test
    void ElevenMillionElem()
    {
        int len = 11000000;
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i)
        {
            arr[i] = rnd.nextInt();
        }
        int[] out = func.sort(arr, arr.length);
        Arrays.sort(arr);
        assertArrayEquals(arr, out);
    }

    @Test
    void TwelveMillionElem()
    {
        int len = 12000000;
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i)
        {
            arr[i] = rnd.nextInt();
        }
        int[] out = func.sort(arr, arr.length);
        Arrays.sort(arr);
        assertArrayEquals(arr, out);
    }

    @Test
    void ThirteenMillionElem()
    {
        int len = 13000000;
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i)
        {
            arr[i] = rnd.nextInt();
        }
        int[] out = func.sort(arr, arr.length);
        Arrays.sort(arr);
        assertArrayEquals(arr, out);
    }
}
