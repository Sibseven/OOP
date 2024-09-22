package nsu.lavrenenkov;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.Test;


/**
 * class for add testing.
 */
public class AddTest {

    @Test
    public void checkAdd() {
        Add add = new Add(new nsu.lavrenenkov.Number(5), new nsu.lavrenenkov.Number(4));
        assertTrue(add.eval(" ") == 9.0);
    }

    @Test
    public void checkDerivative() {
        Add add = new Add(new nsu.lavrenenkov.Number(5), new nsu.lavrenenkov.Number(4));
        assertTrue(Objects.equals(add.derivative("x").toString(), "(0.0+0.0)"));
    }
}
