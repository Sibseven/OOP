import static org.junit.jupiter.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing HashTable.
 *
 */
public class HashTableTest {
    private HashTable<String, Integer> hashTable;
    private static final int RANDOM_ELEMS = 128;

    /**
     * Sets up a table with elements.
     * enough to resize it several times.
     */
    @BeforeEach
    public void setUp() {
        hashTable = new HashTable<>();
        for (int i = 0; i < RANDOM_ELEMS; i++) {
            hashTable.addIfAbsent(String.valueOf(i), i);
        }
        hashTable.addIfAbsent("A", 1);
        hashTable.addIfAbsent("B", 2);
        hashTable.addIfAbsent("C", 3);
        hashTable.addIfAbsent("D", 4);
        hashTable.addIfAbsent("E", 5);
        hashTable.addIfAbsent("F", 6);
    }

    @Test
    public void testGet() {
        assertEquals(hashTable.get("C"), 3);
    }

    @Test
    public void testRemove() {
        hashTable.remove("D", 4);
        assertNull(hashTable.get("D"));

    }

    @Test
    public void testSize() {
        hashTable.update("D", 6);
        assertEquals(hashTable.get("D"), 6);
    }

    @Test
    public void testContainsKey() {
        assertFalse(hashTable.contains("400"));
    }

    @Test
    public void testIterator() {
        int count = 0;
        for (HashTable.Entry<String, Integer> entry : hashTable) {
            count++;
        }
        assertEquals(RANDOM_ELEMS + 6, count);
    }

    @Test
    public void testIteratorBad() {
        assertThrows(ConcurrentModificationException.class, () -> {
            for (HashTable.Entry<String, Integer> entry : hashTable) {
                if (Objects.equals(entry.key(), "A")) {
                    hashTable.update("D", 999);
                }
            }
        });
    }

    @Test
    public void checkToString() {
        HashTable<String, Integer> hashTable2 = new HashTable<>();
        hashTable2.addIfAbsent("A", 1);
        hashTable2.addIfAbsent("B", 2);
        hashTable2.addIfAbsent("C", 3);
        assertEquals("[A:1;B:2;C:3;]", hashTable2.toString());
    }



}
