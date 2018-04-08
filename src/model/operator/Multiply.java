package model.operator;

import model.Element;

/**
 * Leaf class of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Multiply implements Operator {

    /**
     * Multiply.
     */
    public static final String OPERATOR = "ˣ";

    @Override
    public double calculate(Element x1, Element x2) {
        return x1.evaluate() * x2.evaluate();
    }

    /**
     *
     * @return string format of operator
     */
    @Override
    public String toString() {
        return OPERATOR;
    }
}
