import static java.lang.Thread.sleep;

/**
 * Baker class.
 */
public class Baker implements Runnable {
    private int id;
    private int speed;
    private final Warehouse wh;
    private final OrderQueue orderQueue;
    private final Pizzeria boss;

    /**
     * Default constructor.
     *
     * @param id - id of baker
     *
     * @param speed - milliseconds to complete one order
     *
     * @param wh - place to store ready pizzas
     *
     * @param orderQueue - queue to take orders from
     *
     * @param boss - pizzeria
     */
    public Baker(int id, int speed, Warehouse wh, OrderQueue orderQueue, Pizzeria boss) {
        this.id = id;
        this.speed = speed;
        this.wh = wh;
        this.orderQueue = orderQueue;
        this.boss = boss;
    }

    @Override
    public void run() {
        while (boss.isWorking()) {
            Order order = orderQueue.remove();
            bake(order);
        }
        while (orderQueue.size() > 0) {
            Order order = orderQueue.remove();
            bake(order);
        }
        System.out.println("All baked");
    }


    /**
     * Method that imitates baking via sleep, then adds ready order to warehouse.
     *
     * @param order - order to bake
     */
    private void bake(Order order)  {
        try {
            sleep(speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wh.add(order);
        System.out.println("Заказ " + order.getId() + " испечен");
    }
}
