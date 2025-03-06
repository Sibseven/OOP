import lombok.Getter;

public class Order {
    @Getter
    private final int id;

    public Order(int id) {
        this.id = id;
    }

}
