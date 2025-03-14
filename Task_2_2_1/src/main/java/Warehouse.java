import java.util.LinkedList;
import java.util.Queue;

public class Warehouse {
    private final Queue<Order> orders = new LinkedList<>();
    private final int capacity;


    public Warehouse(int capacity) {
        this.capacity = capacity;
    }
    public synchronized void add(Order order) {
        while (capacity == orders.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        orders.add(order);
        notifyAll();
    }
    public synchronized Order remove() {
        while (orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order order = orders.poll();
        notifyAll();
        return order;

    }

    public synchronized int size() {
        return orders.size();
    }
}
