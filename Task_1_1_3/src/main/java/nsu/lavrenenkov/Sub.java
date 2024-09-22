package nsu.lavrenenkov;

/**
 * Class for subtraction.
 */
public class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Builder.
     *
     * @param left - minuend
     *
     * @param right - subtrahend
     */
    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Method for calculating derivative of subtraction.
     *
     * @param variable - variable to differentiate by
     *
     * @return derivative of subtraction.
     */
    @Override
    public Expression derivative(String variable) {
        return new Sub(this.left.derivative(variable),
                this.right.derivative(variable));
    }

    /**
     * Method for evaluating.
     *
     * @param sign - assignation "x = 15; y = 5, etc"
     *
     * @return value of assigned subtraction.
     */
    @Override
    public double eval(String sign) {
        return this.left.eval(sign) - this.right.eval(sign);
    }

    /**
     * Redefinition of toString.
     *
     * @return formatted string "(left-right)
     */
    @Override
    public String toString() {
        return "(" + this.left.toString() + "-" + this.right.toString() + ")";
    }
}
