package model;

/**
 * Interface that groups all elements of the math composite except the operator
 * so that each element will evaluate itself.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public interface Element {

    /**
     * Each element evaluate to a double result.
     *
     * @return evaluated result
     */
    public double evaluate();
}
