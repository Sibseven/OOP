package nsu.lavrenenkov;

import java.util.Scanner;

/**
 * class for console input.
 */
public class Console {

    /**
     * Method for showing derivative and eval of given expression.
     *
     * @param in - default in, not used
     */
    public static void main(String[] in) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Print your expression:");
        String input = scanner.nextLine();

        Parser parser = new Parser();
        Expression expression = parser.parseExpression(input);

        System.out.print("Expression: ");
        expression.print();

        System.out.println("Print your derivative variable:");
        String derivVar = scanner.nextLine();
        Expression deriv = expression.derivative(derivVar);
        deriv.print();

        System.out.println("Print your variable values:");
        String variable = scanner.nextLine();
        System.out.println("Evaluated: " + expression.eval(variable));

    }
}
