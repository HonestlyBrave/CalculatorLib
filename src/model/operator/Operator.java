package model.operator;

import model.Element;

/**
 * Interface for operators of the math composite with method to calculate two
 * elements with an operator.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public interface Operator {

    /**
     *
     * @param element1 first operand
     * @param element2 second operand
     * @return calculated value
     */
    public double calculate(Element element1, Element element2);
}
