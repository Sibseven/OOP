import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class WarehouseTest {
    @Test
    public void limitAddTest() throws InterruptedException {
        Warehouse warehouse = new Warehouse(1, new Pizzeria());
        Thread producer1 = new Thread(() -> {
            warehouse.add(new Order(1));
            System.out.println("Order 1 added by " + Thread.currentThread().getName());
        });
        Thread producer2 = new Thread(() -> {
            warehouse.add(new Order(2));
            System.out.println("Order 2 added by " + Thread.currentThread().getName());
        });

        Thread consumer = new Thread(() -> {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Order order1 = warehouse.remove();
            Order order2 = warehouse.remove();
            System.out.println("Order " + order1.getId() + " removed by " + Thread.currentThread().getName());
            System.out.println("Order " + order2.getId() + " removed by " + Thread.currentThread().getName());
        });

        producer1.start();
        sleep(10);
        producer2.start();
        sleep(10);
        assertEquals(Thread.State.WAITING, producer2.getState());

        consumer.start();

        producer1.join();
        producer2.join();
        consumer.join();

        assertEquals(0, warehouse.size());

    }

    @Test
    public void testAddInterruptedException() throws InterruptedException {
        Warehouse warehouse = new Warehouse(1, new Pizzeria());
        warehouse.add(new Order(1));
        Thread producer = new Thread(() -> {
            warehouse.add(new Order(2));
        });
        producer.start();
        Thread.sleep(100);
        producer.interrupt();
        try {
            producer.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        assertTrue(producer.isInterrupted());
    }
}

