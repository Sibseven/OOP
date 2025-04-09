import lombok.Getter;

/**
 * Class for order.
 */
@Getter
public class Order {
    private final int id;

    public Order(int id) {
        this.id = id;
    }

}
