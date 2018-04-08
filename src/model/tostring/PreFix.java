package model.tostring;

import model.Element;
import model.operator.Operator;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class PreFix implements DisplayStrategy {

    @Override
    public String display(Operator op, Element e1, Element e2) {
        return op + " " + e1 + " " + e2;
    }
}
