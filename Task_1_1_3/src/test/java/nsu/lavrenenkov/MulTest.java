package nsu.lavrenenkov;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * class for testing Mul.
 */
public class MulTest {

    @Test
    public void checkMul() {
        Mul mul = new Mul(new nsu.lavrenenkov.Number(5), new nsu.lavrenenkov.Number(4));
        assertTrue(mul.eval(" ") == 20.0);
    }
}
