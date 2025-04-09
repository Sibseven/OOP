import java.util.LinkedList;
import java.util.Queue;

/**
 * Class with synchronous Queue.
 */
public class OrderQueue {
    private final Queue<Order> orders = new LinkedList<>();
    private Pizzeria pizzeria;

    public OrderQueue(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    /**
     * Method for sync add to Queue.
     *
     * @param order - order to be added in queue.
     */
    public synchronized void add(Order order) {
        orders.add(order);
        notifyAll();
    }

    /**
     * Method for sync removing from Queue and waiting on empty Queue.
     *
     * @return removed order.
     */
    public synchronized Order remove() {
        while (orders.isEmpty() && pizzeria.isWorking()) {
            try {
                wait(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return orders.poll();
    }

    /**
     * Method for sync determine size.
     *
     * @return - size of queue.
     */
    public synchronized int size() {
        return orders.size();
    }
}
