import org.example.Entry;
import org.example.HashTable;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests
{
    Random rnd = new Random();

    @Test
    void EqualsTest() {
        HashTable<String, Number> hashTable1 = new HashTable<>();
        HashTable<String, Number> hashTable2 = new HashTable<>();
        hashTable1.put("one", 1);
        hashTable1.update("one", 1.0);
        hashTable1.update("one", 3.0);
        hashTable2.put("one", 3.0);
        assert hashTable1.equals(hashTable2);
    }

    @Test
    void PutTest() {
        HashTable<String, Number> hashTable = new HashTable<>();
        int cnt = rnd.nextInt(100);
        for (int i = 0; i < cnt; i++){
            hashTable.put(i + "", i);
        }
        for (int i = 0; i < cnt; i++) {
            assertEquals(i, hashTable.get(i + ""));
        }
    }

    @Test
    void ConcurrentModificationTest() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("1", 1);
        hashTable.put("2", 2);
        var iterator = hashTable.iterator();
        hashTable.put("3", 3);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    void ToStringTest() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("1", 1);
        hashTable.put("2", 2);
        hashTable.put("3", 3);
        assert Objects.equals(hashTable.toString(), "{1=1, 2=2, 3=3}");
    }

    @Test
    void RemoveTest() {
        HashTable<String, Number> hashTable = new HashTable<>();
        int cnt = 1 + rnd.nextInt(100);
        int cntRem = rnd.nextInt(cnt);
        for (int i = 0; i < cnt; i++){
            hashTable.put(i + "", i);
        }
        for (int i = 0; i < cntRem; i++){
            hashTable.remove(i + "");
        }
        for (int i = 0; i < cntRem; i++) {
            assert !hashTable.containsKey(i + "");
        }
        for (int i = cntRem; i < cnt; i++) {
            assertEquals(i, hashTable.get(i + ""));
        }
    }

    @Test
    void UpdateTest() {
        HashTable<String, Number> hashTable = new HashTable<>();
        int cnt = 1 + rnd.nextInt(100);
        int cntRem = rnd.nextInt(cnt);
        for (int i = 0; i < cnt; i++){
            hashTable.put(i + "", i);
        }
        for (int i = 0; i < cntRem; i++){
            hashTable.update(i + "", 101);
        }
        for (int i = 0; i < cntRem; i++) {
            assertEquals(101, hashTable.get(i + ""));
        }
        for (int i = cntRem; i < cnt; i++) {
            assertEquals(i, hashTable.get(i + ""));
        }
    }

    @Test
    void IteratorTest() {
        HashTable<String, Number> hashTable = new HashTable<>();
        int cnt = rnd.nextInt(100);
        for (int i = 0; i < cnt; i++){
            hashTable.put(i + "", i);
        }
        int i = 0;
        for (Entry<String, Number> entry : hashTable){
            assert entry != null;
            i++;
        }
        assert i == cnt;
    }
}