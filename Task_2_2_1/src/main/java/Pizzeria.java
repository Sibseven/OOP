import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Getter;

/**
 * Class for pizzeria.
 */
public class Pizzeria {
    @Getter
    private boolean working = true;
    private final List<Baker> bakers = new ArrayList<Baker>();
    private final List<Courier> couriers = new ArrayList<Courier>();
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
        pizzeria.start();
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
        PizzeriaConfig pc = null;
        try {
            pc = objectMapper.readValue(config, PizzeriaConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int storageCapacity = pc.getStorage_capacity();
        this.warehouse = new Warehouse(storageCapacity);
        int idCourier = 1;
        for (CourierConfig courierConfig : pc.getCouriers()) {
            this.couriers.add(new Courier(warehouse, courierConfig.getCapacity(), this, idCourier));
            idCourier++;
        }
        int idBaker = 1;
        for (BakerConfig bakerConfig : pc.getBakers()) {
            this.bakers.add(new Baker(idBaker, bakerConfig.getSpeed(), warehouse, orderQueue, this));
            idBaker++;
        }
        this.workDuratuion = pc.getWorking_time();
    }

    /**
     * Method for running pizzeria.
     *
     * Creates threads for bakers and couriers, simulates order creation.
     */
    public void start()  {

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


        long startTime = System.currentTimeMillis();

        int idOrder = 1;
        Random rand = new Random();
        while (System.currentTimeMillis() - startTime < workDuratuion) {
            try {
                Thread.sleep(rand.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Order order = new Order(idOrder);
            orderQueue.add(order);
            System.out.println("Заказ " + idOrder + " Поступил");
            idOrder++;
        }
        this.working = false;
        synchronized (orderQueue) {
            orderQueue.notifyAll();
        }


        for (Thread thread : threads_baker) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (Thread thread : threads_courier) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
