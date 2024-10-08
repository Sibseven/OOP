package ru.nsu.lavrenenkov.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * class for add testing.
 */
public class AddTest {

    @Test
    public void checkAdd() {
        Add add = new Add(new Number(5), new Number(4));
        assertEquals(9.0, add.eval(" "));
    }

    @Test
    public void checkDerivative() {
        Add add = new Add(new Number(5), new Number(4));
        Expression actual = add.derivative(" ");
        Add expected = new Add(new Number(0), new Number(0));
        assertEquals(expected, actual);
    }
}
