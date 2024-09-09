import org.example.HeapSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class UnitTests {
    HeapSort func = new HeapSort();
    @Test
    void TwoElemsArray()
    {
        int[] out = {511,-2};
        func.sort(out, out.length);
        assertArrayEquals(new int[] {-2, 511}, out);
    }

    @Test
    void TestEmptyArray()
    {
        int[] out = {};
        func.sort(out, out.length);
        assertArrayEquals(new int[] {}, out);
    }

    @Test
    void TestNegativeArray()
    {
        int[] out = {7, 5, -3, -1, -4};
        func.sort(out, out.length);
        assertArrayEquals(new int[] {-4, -3, -1, 5, 7}, out);
    }

    @Test
    void TestBigArray()
    {
        int[] out = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        func.sort(out, out.length);
        assertArrayEquals(new int[] {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10}, out);
    }

    @Test
    void TestSameArray()
    {
        int[] out = {-1,-1,-1,-1,-1,-1};
        func.sort(out, out.length);
        assertArrayEquals(new int[] {-1,-1,-1,-1,-1,-1}, out);
    }
}
