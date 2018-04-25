package model.exponent;

import model.Element;

/**
 * Interface for exponents of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public interface Exponent extends Element {

    /**
     *
     * @return current Element
     */
    public Element getElement();

    /**
     * Whether current element is itself an exponent.
     *
     * @return boolean
     */
    public boolean isNotExponent();

}
