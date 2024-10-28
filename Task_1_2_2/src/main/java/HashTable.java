import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class HashTable<K, V>  implements Iterable<HashTable.Entry<K, V>>{
    private static final double LOAD_FACTOR = 0.7;
    private int size;
    private int capacity;
    private List<ArrayList<Entry<K, V>>> table;


    public HashTable() {
        this.size = 0;
        this.capacity = 16;
        this.table = new ArrayList<>();

    }

    public record Entry<K, V>(K key, V value){
        @Override
        public int hashCode() {
            return key.hashCode();
        }
        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    public void add(K key, V value) {
        if(this.get(key) != null) {
            return;
        }
        if((double) size / capacity >= LOAD_FACTOR) {
            resize(2 * capacity);
        }
        int hash = key.hashCode();
        int index = hash % capacity;
        table.get(index).add(new Entry<>(key, value));
        this.size++;
    }


    public @Nullable V get(K key) {
        int hash = key.hashCode();
        for (Entry<K, V> entry : table.get(hash)) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public void update(K key, V value) {
        remove(key, get(key));
        add(key, value);
    }

    public void resize(int newCapacity) {
        capacity = newCapacity;
        ArrayList<ArrayList<Entry<K,V>>> newTable = new ArrayList<>();
        for (ArrayList<Entry<K, V>> list : table) {
            for (Entry<K, V> entry : list) {
                newTable.get(entry.value.hashCode() % capacity).add(entry);
            }
        }
        table = newTable;
    }

    public void remove(K key, V value) {
        int hash = key.hashCode();
        int index = hash % capacity;
        table.get(index).removeIf(x -> x.key.equals(key) && x.value.equals(value));
    }


    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (ArrayList<Entry<K, V>> list : table) {
            for (Entry<K, V> entry : list) {
                sb.append(entry.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        
    }
}
