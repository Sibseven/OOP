import java.util.LinkedList;
import java.util.Queue;

public class Warehouse {
    private final Queue<Order> orders = new LinkedList<>();
    private int capacity;


    public Warehouse(int capacity) {
        this.capacity = capacity;
    }
    public synchronized boolean tryadd(Order order) {
        if (capacity == orders.size()) {
            return false;
        }
        orders.add(order);
        return true;
    }
    public synchronized Order remove() {
        notify();
        return orders.poll();
    }
}
