package ru.nsu.lavrenenkov.expression;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * Class for testing Sub.
 */
public class SubTest {

    @Test
    public void checkSub() {
        Sub sub = new Sub(new Number(5), new Number(4));
        assertEquals(1.0, sub.eval(" "));
    }

    @Test
    public void checkDerivative() {
        Sub sub = new Sub(new Number(5), new Number(4));
        assertEquals(new Sub(new Number(0), new Number(0)), sub.derivative("x"));
    }
}
