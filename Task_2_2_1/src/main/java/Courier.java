import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.List;

/**
 * Courier class.
 */
public class Courier implements Runnable {
    private final int id;
    private final int capacity;
    private final Warehouse wh;
    private final Pizzeria boss;


    /**
     * Default constructor.
     *
     * @param wh - place to get pizzas from
     *
     * @param capacity - how many orders can courier take for one delivery.
     *
     * @param boss - pizzeria
     *
     * @param id - id of courier
     */
    public Courier(Warehouse wh, int capacity, Pizzeria boss, int id) {
        this.id = id;
        this.capacity = capacity;
        this.boss = boss;
        this.wh = wh;
    }

    @Override
    public void run() {
        while (boss.isWorking()) {
            takePizzas();
        }
        while (wh.size() > 0) {
            takePizzas();
        }

    }

    /**
     * Method for courier to take pizzas no more than capacity then deliver them.
     */
    private void takePizzas() {
        List<Order> load = new ArrayList<Order>();
        for (int i = 0; i < capacity; i++) {
            Order order = wh.remove();
            load.add(order);
            if (wh.size() == 0) {
                break;
            }
        }
        deliver(load);
    }

    /**
     * Method to imitate delivery via sleep.
     *
     * @param load - order to deliver
     */
    private void deliver(List<Order> load) {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Order order : load) {
            if (order == null) {
                continue;
            }
            System.out.println("Заказ " + order.getId() + " Доставлен");
        }
    }
}
