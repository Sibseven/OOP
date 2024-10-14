package ru.nsu.lavrenenkov.expression;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * class for testing Mul.
 */
public class MulTest {

    @Test
    public void checkMul() {
        Mul mul = new Mul(new Number(5), new Number(4));
        assertTrue(mul.eval(" ") == 20.0);
    }

    @Test
    public void checkHashCode() {
        Mul mul = new Mul(new Number(5), new Number(4));
        assertNotEquals(mul.hashCode(), new Object().hashCode());
    }
}
