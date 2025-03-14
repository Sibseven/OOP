import java.util.Random;


/**
 * Class for generating orders.
 */
public class OrderGenerator implements Runnable{
    Pizzeria pizzeria;
    int idNow = 1;

    /**
     * Default constructor.
     *
     * @param pizzeria - add orders to
     */
    public OrderGenerator(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    @Override
    public void run() {
        while (pizzeria.isWorking()) {
            generateOrder();
        }
    }

    /**
     * Method for pceudoRandom order generation
     */
    public void generateOrder() {
        Random rand = new Random();
        try {
            Thread.sleep(rand.nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Order order = new Order(idNow);
        pizzeria.takeOrder(order);
        System.out.println("Заказ " + idNow + " Поступил");
        idNow++;
    }
}
