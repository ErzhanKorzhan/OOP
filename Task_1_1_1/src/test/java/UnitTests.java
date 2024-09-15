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
    void OneElem()
    {
        int len = 1;
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i)
        {
            arr[i] = rnd.nextInt();
        }
        int[] out = arr.clone();
        func.sort(out, out.length);
        Arrays.sort(arr);
        assertArrayEquals(arr, out);
    }

    @Test
    void ThousElem()
    {
        int len = 1000;
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i)
        {
            arr[i] = rnd.nextInt();
        }
        int[] out = arr.clone();
        func.sort(out, out.length);
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
        int[] out = arr.clone();
        func.sort(out, out.length);
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
        int[] out = arr.clone();
        func.sort(out, out.length);
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
        int[] out = arr.clone();
        func.sort(out, out.length);
        Arrays.sort(arr);
        assertArrayEquals(arr, out);
    }
}
