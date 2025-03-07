import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class for testing pizzeria.
 */
public class PizzeriaTest {
    private ByteArrayOutputStream outputStream;
    Pizzeria pizzeria;

    @BeforeEach
    public void setUp() {
        pizzeria = new Pizzeria();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void test() {
        pizzeria.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String output = outputStream.toString();
        assertTrue(output.contains("Доставлен"));
        assertTrue(output.contains("испечен"));

    }


}
