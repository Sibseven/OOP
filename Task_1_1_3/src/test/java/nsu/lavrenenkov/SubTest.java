package nsu.lavrenenkov;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * Class for testing Sub.
 */
public class SubTest {

    @Test
    public void checkSub() {
        Sub sub = new Sub(new nsu.lavrenenkov.Number(5), new nsu.lavrenenkov.Number(4));
        assertTrue(sub.eval(" ") == 1.0);
    }

    @Test
    public void checkDerivative() {
        Sub sub = new Sub(new nsu.lavrenenkov.Number(5), new nsu.lavrenenkov.Number(4));
        assertTrue(Objects.equals(sub.derivative("x").toString(), "(0.0-0.0)"));
    }
}
