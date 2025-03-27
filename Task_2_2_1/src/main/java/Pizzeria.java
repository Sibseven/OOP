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
        int storageCapacity = pc.getStorageCapacity();
        this.warehouse = new Warehouse(storageCapacity, this);
        for (PizzeriaConfig.CourierConfig courierConfig : pc.getCouriers()) {
            this.couriers.add(new Courier(warehouse, courierConfig.getCapacity(),
                        this, courierConfig.getId()));
        }
        for (PizzeriaConfig.BakerConfig bakerConfig : pc.getBakers()) {
            this.bakers.add(new Baker(bakerConfig.getId(), bakerConfig.getSpeed(),
                            warehouse, orderQueue, this));
        }
        this.workDuratuion = pc.getWorkingTime();
    }

    /**
     * Method for running pizzeria.
     * Creates threads for bakers and couriers.
     */
    public void start() throws InterruptedException {

        readConfig();
        List<Thread> threadsBaker = new ArrayList<>();
        for (Baker baker : this.bakers) {
            threadsBaker.add(new Thread(baker));
            threadsBaker.getLast().start();
        }
        List<Thread> threadsCourier = new ArrayList<>();
        for (Courier courier : this.couriers) {
            threadsCourier.add(new Thread(courier));
            threadsCourier.getLast().start();
        }
        Thread.sleep(workDuratuion);
        this.working = false;
        synchronized (orderQueue) {
            orderQueue.notifyAll();
        }


        for (Thread thread : threadsBaker) {
            thread.join();
        }

        for (Thread thread : threadsCourier) {
            thread.join();
        }
        System.out.println("WorkEnd");
    }

    public void takeOrder(Order order) {
        orderQueue.add(order);
    }


}
