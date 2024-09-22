package nsu.lavrenenkov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * class for testing Parser.
 */
public class ParserTest {

    @Test
    public void checkParser() {
        Parser parser = new Parser();
        Expression expression = parser.parseExpression("(((2/3)*(10-5))+y)");
        Add expected = new Add( new Mul(new Div(new Number(2), new Number(3)),
                                        new Sub(new Number(10), new Number(5))),
                new Variable("y"));

        assertEquals(expression.toString(), expected.toString());
    }
}
