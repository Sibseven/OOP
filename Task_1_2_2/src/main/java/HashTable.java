import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Class for HashTable.
 *
 * @param <K> - type of keys
 *
 * @param <V> - type of values
 */
public class HashTable<K, V>  implements Iterable<HashTable.Entry<K, V>> {
    private static final double LOAD_FACTOR = 0.7;
    private int numOfElements;
    private int numOfChanges;
    private int capacity;
    private List<ArrayList<Entry<K, V>>> table;


    public HashTable() {
        this.numOfElements = 0;
        this.numOfChanges = 0;
        this.capacity = 16;
        this.table = new ArrayList<>();
        for (int i = 0; i < this.capacity; i++) {
            this.table.add(new ArrayList<>());
        }
    }

    /**
     * Record for holding single key-value pair.
     *
     * @param key - key
     *
     * @param value - value
     *
     * @param <K> - type of key
     *
     * @param <V> - type of value
     */
    public record Entry<K, V>(K key, V value) {
        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    /**
     * Adds key-value pair to hashTable,
     * if a value with such key exist does nothing
     *
     * @param key - key
     * @param value - value
     */
    public void addIfAbsent(K key, V value) {
        if (this.get(key) != null) {
            return;
        }
        if ((double) numOfElements / capacity >= LOAD_FACTOR) {
            resize(2 * capacity);
        }
        int hash = key.hashCode();
        int index = hash % capacity;
        table.get(index).add(new Entry<>(key, value));
        this.numOfElements++;
        numOfChanges++;
    }


    public @Nullable V get(K key) {
        int hash = key.hashCode();
        int index = hash % capacity;
        for (Entry<K, V> entry : table.get(index)) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    /**
     * Method for checking if table contains key.
     *
     * @param key - key to check
     *
     * @return true if contains, false otherwise
     */
    public boolean contains(K key) {
        int hash = key.hashCode();
        int index = hash % capacity;
        for (Entry<K, V> entry : table.get(index)) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates value by key.
     *
     * @param key - key
     *
     * @param value - value
     */
    public void update(K key, V value) {
        remove(key, get(key));
        addIfAbsent(key, value);
    }

    /**
     * Removes key-value pair from table.
     * If no such pair, does nothing.
     *
     * @param key - key
     *
     * @param value - value
     */
    public void remove(K key, V value) {
        numOfChanges++;
        int hash = key.hashCode();
        int index = hash % capacity;
        table.get(index).removeIf(x -> x.key.equals(key) && x.value.equals(value));
    }

    /**
     * Method for resizing and rehashing whole table.
     *
     * @param newCapacity - newCapacity
     */
    private void resize(int newCapacity) {
        capacity = newCapacity;
        ArrayList<ArrayList<Entry<K, V>>> newTable = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            newTable.add(new ArrayList<>());
        }
        for (ArrayList<Entry<K, V>> list : table) {
            for (Entry<K, V> entry : list) {
                newTable.get(entry.value.hashCode() % capacity).add(entry);
            }
        }
        table = newTable;
    }




    @Override
    public @NotNull Iterator<Entry<K, V>> iterator() {
        return new HashTableIterator();
    }

    /**
     * Class for Iteration through Hashtable.
     */
    private class HashTableIterator implements Iterator<Entry<K, V>> {
        private Entry<K, V> now;
        private int indexCount = 0;
        private final int checkValue;
        private final int numOfElements;
        private int indexChain = 0;
        private int indexCell = 0;

        /**
         * Builder.
         */
        HashTableIterator() {
            numOfElements = HashTable.this.numOfElements;
            checkValue = numOfChanges;
        }

        @Override
        public boolean hasNext() {
            check();
            return indexCount < numOfElements;
        }

        @Override
        public Entry<K, V> next() {
            check();
            do {
                indexCell++;
                if (indexCell >= table.get(indexChain).size()) {
                    indexChain = (indexChain + 1) % table.size();
                    indexCell = 0;
                }
            } while (table.get(indexChain).isEmpty());
            now = table.get(indexChain).get(indexCell);
            indexCount++;
            return now;
        }

        /**
         * Method to check if modification was done.
         *
         * @throws ConcurrentModificationException if modification found
         */
        private void check() throws ConcurrentModificationException {
            if (HashTable.this.numOfChanges != checkValue) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (ArrayList<Entry<K, V>> list : table) {
            for (Entry<K, V> entry : list) {
                sb.append(entry.toString());
                sb.append(";");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfElements, capacity, table);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashTable<?, ?> hashTable)) return false;
        return numOfElements == hashTable.numOfElements && capacity == hashTable.capacity && Objects.equals(table, hashTable.table);
    }
}
