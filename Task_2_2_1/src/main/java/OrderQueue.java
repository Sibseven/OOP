import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private final Queue<Order> orders = new LinkedList<>();

    public synchronized void add(Order order) {
        orders.add(order);
        notify();
    }
    public synchronized Order remove() {
        return orders.poll();
    }
}
