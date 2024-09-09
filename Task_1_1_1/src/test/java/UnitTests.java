import org.example.HeapSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class UnitTests {
    @Test
    void TwoElemsArray()
    {
        int[] out = {511,-2};
        func.sort(out, out.length);
        assertArrayEquals(new int[] {-2, 511}, out);
    }

    HeapSort func = new HeapSort();
    @Test
    void TestNegativeArray()
    {
        int[] out = {7, 5, -3, -1, -4};
        func.sort(out, out.length);
        assertArrayEquals(new int[] {-4, -3, -1, 5, 7}, out);
    }

    @Test
    void TestClassArray()
    {
        int[] out = {5, 4, 3, 2, 1};
        func.sort(out, out.length);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, out);
    }

    @Test
    void TestEmptyArray()
    {
        int[] out = {};
        func.sort(out, out.length);
        assertArrayEquals(new int[] {}, out);
    }

    @Test
    void TestSameArray()
    {
        int[] out = {-1,-1,-1,-1,-1,-1};
        func.sort(out, out.length);
        assertArrayEquals(new int[] {-1,-1,-1,-1,-1,-1}, out);
    }
}
