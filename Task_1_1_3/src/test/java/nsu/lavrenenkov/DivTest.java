package nsu.lavrenenkov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * class for testing Div.
 */
public class DivTest {

    @Test
    public void checkEval() {
        Div div = new Div(new nsu.lavrenenkov.Number(20), new nsu.lavrenenkov.Number(4));
        assertEquals(div.eval(" "), 5.0);
    }

    @Test
    public void checkDerivative() {
        Div div = new Div(new nsu.lavrenenkov.Number(20), new nsu.lavrenenkov.Number(4));
        Expression expected = new Div(new Sub(
                                      new Mul(new Number(0), new Number(4)),
                                      new Mul(new Number(20), new Number(0))),
                new Mul(new Number(4), new Number(4)));
        Expression actual = div.derivative(" ");
        assertEquals(expected.toString(), actual.toString());
    }
}
