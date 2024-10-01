package ru.nsu.lavrenenkov.expression;

/**
 * Abstract class for Expresisons.
 */
public abstract class Expression {

    /**
     * Method for printing expression.
     */
    public void print() {
        System.out.println(this.toString());
    }

    /**
     * Abstract method for making new derivative from given expression.
     *
     * @param variable - variable to differentiate by
     *
     * @return - derivative
     */
    public abstract Expression derivative(String variable);

    /**
     * Abstract method for evaluation.
     *
     * @param sign - assignation "x = 15; y = 5, etc"
     *
     * @return - value of evaluated expression.
     */
    public abstract double eval(String sign);
}
