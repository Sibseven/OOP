package nsu.lavrenenkov;

/**
 * class for numbers.
 * contains one field of double(value of number)
 */
public class Number extends Expression{
    final double value;

    /**
     *
     * @param value - value of number
     */
    public Number(double value) {
        this.value = value;
    }

    /**
     * Method for calculating derivative of number.
     *
     * @param value - variable to differentiate by
     *
     * @return 0, because derivative of constant is 0
     */
    @Override
    public Number derivative(String value) {
        return new Number(0);
    }

    /**
     * Method for evaluating numbers.
     *
     * @param value - assignation "x = 15; y = 5, etc"
     *
     * @return value of number
     */
    @Override
    public double eval(String value) {
        return this.value;
    }

    /**
     * Redefinition of toString.
     *
     * @return string of value field
     */
    @Override
    public String toString() {
        return Double.toString(value);
    }
}
