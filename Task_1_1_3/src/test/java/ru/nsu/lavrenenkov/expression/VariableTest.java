package ru.nsu.lavrenenkov.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * class for testing variable.
 */
public class VariableTest {

    @Test
    public void checkEval() {
        Variable var1 = new Variable("x");
        Variable var2 = new Variable("y");

        assertEquals(10.0, var1.eval("x = 10"));
        assertEquals(0.0, var2.eval("x = 10"));
    }

    @Test
    public void checkDerivative() {
        Variable var1 = new Variable("x");
        Variable var2 = new Variable("y");

        assertEquals(var1.derivative("x").toString(), "1.0");
        assertEquals(var2.derivative("x").toString(), "0.0");
    }
}
