package nsu.lavrenenkov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * class for testing console.
 */
public class ConsoleTest {

    @Test
    public void checkMain() {

        String input = "(x / (2 * 7))\nx\nx = 28";
        final InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));


        String expectedOutput = "Print your expression:\n"
                + "Expression: (x/(2.0*7.0))\n"
                + "Print your derivative variable:\n"
                + "(((1.0*(2.0*7.0))-(x*((0.0*7.0)+(2.0*0.0))))/((2.0*7.0)*(2.0*7.0)))\n"
                + "Print your variable values:\n"
                + "Evaluated: 2.0\n";

        Console.main(new String[0]);

        String actualOutput = outContent.toString();

        assertEquals(actualOutput, expectedOutput);

        System.setIn(originalIn);
        System.setOut(originalOut);



    }
}
