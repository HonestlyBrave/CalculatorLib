package model;

/**
 * Interface for leaf elements of the math composite except the operator.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public interface Element {

    /**
     * Each element evaluates to a double result.
     *
     * @return evaluated result
     */
    public double evaluate();
}
