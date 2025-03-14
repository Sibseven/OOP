import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Class for pizzeria.
 */
public class Pizzeria {
    @Getter
    private boolean working = true;
    private final List<Baker> bakers = new ArrayList<>();
    private final List<Courier> couriers = new ArrayList<>();
    private Warehouse warehouse;
    private final OrderQueue orderQueue = new OrderQueue(this);
    private int workDuratuion;

    /**
     * Default main.
     *
     * @param args - default args
     */
    public static void main(String[] args)  {
        Pizzeria pizzeria = new Pizzeria();
        OrderGenerator orderGenerator = new OrderGenerator(pizzeria);
        Thread thread = new Thread(orderGenerator);
        try {
            thread.start();
            pizzeria.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for reading info about pizzeria from json.
     */
    private void readConfig() {
        File config = null;
        try {
            config = new File(getClass().getClassLoader().getResource("config.json").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        PizzeriaConfig pc;
        try {
            pc = objectMapper.readValue(config, PizzeriaConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int storageCapacity = pc.getStorage_capacity();
        this.warehouse = new Warehouse(storageCapacity, this);
        for (PizzeriaConfig.CourierConfig courierConfig : pc.getCouriers()) {
            this.couriers.add(new Courier(warehouse, courierConfig.getCapacity(), this, courierConfig.getId()));
        }
        for (PizzeriaConfig.BakerConfig bakerConfig : pc.getBakers()) {
            this.bakers.add(new Baker(bakerConfig.getId(), bakerConfig.getSpeed(), warehouse, orderQueue, this));
        }
        this.workDuratuion = pc.getWorking_time();
    }

    /**
     * Method for running pizzeria.
     * Creates threads for bakers and couriers.
     */
    public void start() throws InterruptedException {

        readConfig();
        List<Thread> threads_baker = new ArrayList<>();
        for (Baker baker : this.bakers) {
            threads_baker.add(new Thread(baker));
            threads_baker.getLast().start();
        }
        List<Thread> threads_courier = new ArrayList<>();
        for (Courier courier : this.couriers) {
            threads_courier.add(new Thread(courier));
            threads_courier.getLast().start();
        }
        Thread.sleep(workDuratuion);
        this.working = false;
        synchronized (orderQueue) {
            orderQueue.notifyAll();
        }


        for (Thread thread : threads_baker) {
            thread.join();
        }

        for (Thread thread : threads_courier) {
            thread.join();
        }
        System.out.println("WorkEnd");
    }

    public void takeOrder(Order order) {
        orderQueue.add(order);
    }


}
