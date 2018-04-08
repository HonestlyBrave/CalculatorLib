package model.tostring;

import model.Element;
import model.operator.Operator;

/**
 * Interface to regroup all display strategies.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public interface DisplayStrategy {

    /**
     *
     * @param op operator
     * @param e1 element
     * @param e2 element
     * @return concatenated string of base expression
     */
    public String display(Operator op, Element e1, Element e2);
}
