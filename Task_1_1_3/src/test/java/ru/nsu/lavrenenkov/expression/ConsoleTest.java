package ru.nsu.lavrenenkov.expression;

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

        String input = "(x / (2 * 7))\nx\nx = 28\n";
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

    @Test
    public void checkMain2() {
        String input = "((((a+5)*(3-2))/(7+1))+10)\nx\nx = 28\n";
        final InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));


        String expectedOutput = "Print your expression:\n"
                + "Expression: ((((a+5.0)*(3.0-2.0))/(7.0+1.0))+10.0)\n"
                + "Print your derivative variable:\n"
                + "(((((((0.0+0.0)*(3.0-2.0))+((a+5.0)*(0.0-0.0)))*(7.0+1.0))-(((a+5.0)*(3.0-2.0))*(0.0+0.0)))/((7.0+1.0)*(7.0+1.0)))+0.0)\n"
                + "Print your variable values:\n"
                + "Evaluated: 10.625\n";

        Console.main(new String[0]);

        String actualOutput = outContent.toString();

        assertEquals(actualOutput, expectedOutput);

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void checkMain3() {
        String input = "(((a/b)+((c*d)-8))*(((b+2)-(c/4))+(10*3)))\na\na = 10; b = 5; c = 3; d = 35; t = 1\n";
        final InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));


        String expectedOutput = "Print your expression:\n"
                + "Expression: (((a/b)+((c*d)-8.0))*(((b+2.0)-(c/4.0))+(10.0*3.0)))\n"
                + "Print your derivative variable:\n"
                + "((((((1.0*b)-(a*0.0))/(b*b))+(((0.0*d)+(c*0.0))-0.0))*(((b+2.0)-(c/4.0))+(10.0*3.0)))+(((a/b)+((c*d)-8.0))*(((0.0+0.0)-(((0.0*4.0)-(c*0.0))/(4.0*4.0)))+((0.0*3.0)+(10.0*0.0)))))\n"
                + "Print your variable values:\n"
                + "Evaluated: 3588.75\n";

        Console.main(new String[0]);

        String actualOutput = outContent.toString();

        assertEquals(actualOutput, expectedOutput);

        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
