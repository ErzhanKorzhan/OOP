import org.example.FileSubstringSearch;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests
{
    @Test
    public void NullTest() {
        List<Integer> indices = FileSubstringSearch.find("input.txt", "help");
        assert indices.isEmpty();
    }

    @Test
    public void FifteenCharsTest() {
        List<Integer> indices = FileSubstringSearch.find("input.txt", "abra");
        assert indices.size() == 2;
        assertTrue(indices.contains(0));
        assertTrue(indices.contains(8));
    }

    @Test
    public void TenThousandsCharsTest() throws IOException {
        try (FileWriter writer = new FileWriter("test.txt", true)) {
            writer.write("лол".repeat(10000));
        }
        List<Integer> indices = FileSubstringSearch.find("test.txt", "лл");
        File file = new File("test.txt");
        if (file.exists()) {
            assertTrue(file.delete());
        }
        assert indices.size() == 9999;
    }

    @Test
    public void OneMillionCharsTest() throws IOException {
        try (FileWriter writer = new FileWriter("test.txt", true)) {
            writer.write("abra".repeat(1000000));
        }
        List<Integer> indices = FileSubstringSearch.find("test.txt", "br");
        File file = new File("test.txt");
        if (file.exists()) {
            assertTrue(file.delete());
        }
        assert indices.size() == 1000000;
    }

    @Test
    public void EngSmilesTest() {
        List<Integer> indices = FileSubstringSearch.find("eng_and_smiles.txt", "bC🙂😉");
        assert indices.size() == 3;
        assertTrue(indices.contains(7));
        assertTrue(indices.contains(25));
        assertTrue(indices.contains(52));
    }

    @Test
    public void ChineseTest() {
        List<Integer> indices = FileSubstringSearch.find("chinese.txt", "䜔䛶䜌䜀");
        assert indices.size() == 2;
        assertTrue(indices.contains(0));
        assertTrue(indices.contains(12));
    }
}