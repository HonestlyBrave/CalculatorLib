package factory;

import model.Element;
import model.exponent.*;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class ExponentFactory {

    public static Exponent createExponent(int type, Element value) {

        switch (type) {
            case 2:
                return new Squared(value);
            case 3:
                return new Cubed(value);
            default:
                return null;
        }

    }

}
