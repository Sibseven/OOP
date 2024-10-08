package ru.nsu.lavrenenkov.expression;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for variables.
 */
public class Variable extends Expression {
    private final String variableName;

    /**
     * Builder.
     *
     * @param name - name of variable.
     */
    public Variable(String name) {
        this.variableName = name;
    }

    /**
     * Method for calculating derivative.
     *
     * @param variable - variable to differentiate by
     *
     * @return derivative of variable
     */
    @Override
    public Expression derivative(String variable) {
        if (variableName.equals(variable)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    /**
     * Method for parsing assignation.
     *
     * @param signification - assignation "x = 15; y = 5, etc"
     *
     * @return 0 if variable not assigned, else assigned value.
     */
    @Override
    public double eval(String signification) {
        Pattern pattern = Pattern.compile("([a-zA-Z_0-9]) = ([0-9]+\\.?[0-9]*)");
        Matcher matcher = pattern.matcher(signification);

        String variable;
        double value = 0;

        while (matcher.find()) {
            variable = matcher.group(1);
            if (variable.equals(this.variableName)) {
                value  = Double.parseDouble(matcher.group(2));
                break;
            }
        }
        return value;
    }

    /**
     * Redefinition of toString.
     *
     * @return variable name
     */
    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;

        return Objects.equals(variable.variableName, variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName);
    }
}
