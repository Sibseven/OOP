package ru.nsu.lavrenenkov.expression;

/**
 * Class for multiplication.
 */
public class Mul extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Builder.
     *
     * @param left - first factor
     * @param right - second factor.
     */
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Method for calculating derivative of multiplication.
     *
     * @param variable - variable to differentiate by
     *
     * @return derivative of multiplication.
     */
    @Override
    public Expression derivative(String variable) {
        return new Add(new Mul(this.left.derivative(variable), this.right),
                       new Mul(this.left, this.right.derivative(variable)));
    }

    /**
     * Method for evaluation.
     *
     * @param sign - assignation "x = 15; y = 5, etc"
     *
     * @return evaluated multiplication.
     */
    @Override
    public double eval(String sign) {
        return this.left.eval(sign) * this.right.eval(sign);
    }

    /**
     * Redefinition of toString.
     *
     * @return formatted string "(left*right)
     */
    @Override
    public String toString() {
        return "(" + this.left.toString() + "*" + this.right.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mul mul = (Mul) o;

        return left.equals(mul.left) && right.equals(mul.right);
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
