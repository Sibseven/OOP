package ru.nsu.lavrenenkov.expression;

/**
 * Div class.
 * contains left and right part of div
 */
public class Div extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Builder.
     *
     * @param left numerator
     *
     * @param right denominator
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Method for making a derivative of division.
     *
     * @param variable - variable to differentiate by.
     *
     * @return new differentiated expression
     */
    @Override
    public Expression derivative(String variable) {
        return new Div(
                new Sub(new Mul(this.left.derivative(variable), this.right),
                        new Mul(this.left, this.right.derivative(variable))),
                new Mul(this.right, this.right));
    }

    /**
     * Method for evaluating division.
     *
     * @param sign - assignation
     *
     * @return value of assigned division.
     */
    @Override
    public double eval(String sign) {
        return this.left.eval(sign) / this.right.eval(sign);
    }

    /**
     * Redefinition of toString.
     *
     * @return formatted string "(left/right)
     */
    @Override
    public String toString() {
        return "(" + this.left.toString() + "/" + this.right.toString() + ")";
    }
}
