import static java.lang.Thread.sleep;

public class Baker implements Runnable {
    private int id;
    private int speed;
    private final Warehouse wh;
    private final OrderQueue orderQueue;
    private boolean running;

    public Baker(int id, int speed, Warehouse wh, OrderQueue orderQueue) {
        this.id = id;
        this.speed = speed;
        this.wh = wh;
        this.orderQueue = orderQueue;
    }
    @Override
    public void run() {
        while (running) {
            try {
                Order order = orderQueue.remove();
                if (order == null) {
                    synchronized (orderQueue) {
                        orderQueue.wait();
                    }
                    continue;
                }

                bake(order);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void bake(Order order) throws InterruptedException {
        sleep(speed);
        synchronized (wh) {
            while (!wh.tryadd(order)) {
                wh.wait();
            }
            wh.notifyAll();
        }
    }
}
