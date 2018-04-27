package model.operator;

import model.Element;

/**
 * Leaf class of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Subtract implements Operator {

    /**
     * Subtract.
     */
    public static final String OPERATOR = " - ";

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
    @Override
    public double calculate(Element x1, Element x2) {
        return x1.evaluate() - x2.evaluate();
    }

    /**
     *
     * @return operator as string
     */
    @Override
    public String toString() {
        return OPERATOR;
    }
    // </editor-fold>
}
