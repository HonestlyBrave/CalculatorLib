package model.operator;

import model.Element;

/**
 * Interface for operators of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public interface Operator {

    /**
     * Method to calculate two elements with an operator.
     *
     * @param element1 first operand
     * @param element2 second operand
     * @return calculated value
     */
    public double calculate(Element element1, Element element2);
}
