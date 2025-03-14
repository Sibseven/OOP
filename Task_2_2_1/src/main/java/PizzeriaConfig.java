import java.util.List;
import lombok.Getter;

/**
 * Class for reading pizzeria config form json.
 */
@Getter
public class PizzeriaConfig {
    private List<BakerConfig> bakers;
    private List<CourierConfig> couriers;
    private int storage_capacity;
    private int working_time;


    /**
     * Class for reading baker from json.
     */
    @Getter
    public static class BakerConfig {
        private int id;
        private int speed;
    }
    /**
     * Class for reading courier info form json.
     */
    @Getter
    public static class CourierConfig {
        private int id;
        private int capacity;
    }
}

