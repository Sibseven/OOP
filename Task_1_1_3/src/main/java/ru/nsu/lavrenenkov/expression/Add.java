package ru.nsu.lavrenenkov.expression;

/**
 * class for add.
 * contains left and right expressions
 */
public class Add extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Builder.
     *
     * @param left - expression on the left
     *
     * @param right - expression on the right
     */
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Method to get derivative of addition.
     *
     * @param variable - variable to differentiate by.
     *
     * @return new add with left and right derivative.
     */
    @Override
    public Expression derivative(String variable) {
        return new Add(this.left.derivative(variable),
                       this.right.derivative(variable));
    }

    /**
     *Method for evaluation.
     *
     * @param sign - assignation
     *
     * @return value of add with given assignation
     */
    @Override
    public double eval(String sign) {
        return this.left.eval(sign) + this.right.eval(sign);
    }

    /**
     * Redefinition of toString.
     *
     * @return formatted string "(left+right)
     */
    @Override
    public String toString() {
        return "(" + this.left.toString() + "+" + this.right.toString() + ")";
    }
}
