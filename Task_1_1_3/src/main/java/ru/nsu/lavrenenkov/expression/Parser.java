package ru.nsu.lavrenenkov.expression;


/**
 * Class for parsing expression from string.
 */
public class Parser {

    /**
     * Method to determine if string a number.
     *
     * @param str - string to check
     *
     * @return true/false
     */
    private boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method for recursive parsing of a string.
     *
     * @param in string to parse.
     *
     * @return expression object
     */
    public Expression parseExpression(String in) {
        in = in.replace(" ", "");

        if (isNumber(in)) {
            return new Number(Double.parseDouble(in));
        }

        if (in.length() == 1) {
            return new Variable(in);
        }

        String left;
        String right;
        char oper = ' ';

        int countOpen = 0;
        int countClosed = 0;
        int rightPos = 0;

        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if (c == '(') {
                countOpen++;
            } else if (c == ')') {
                countClosed++;
            } else if (countOpen - countClosed == 1
                    && "+-*/".contains(String.valueOf(c))) {
                oper = c;
                rightPos = i;
            }
        }

        left = in.substring(1, rightPos);
        right = in.substring(rightPos + 1, in.length() - 1);

        if (oper == '+') {
            return new Add(parseExpression(left), parseExpression(right));
        }
        if (oper == '-') {
            return new Sub(parseExpression(left), parseExpression(right));
        }
        if (oper == '*') {
            return new Mul(parseExpression(left), parseExpression(right));
        }
        if (oper == '/') {
            return new Div(parseExpression(left), parseExpression(right));
        }
        return new Number(0);
    }
}
