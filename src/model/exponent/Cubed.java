package model.exponent;

import model.Element;
import model.Equation;

/**
 * Leaf class of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Cubed implements Exponent {

    /**
     * Superscript.
     */
    public static final String SUPERSCRIPT = "³";

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * Element to be evaluated.
     */
    private final Element element;

    /**
     * False if this element is an exponent.
     */
    private final boolean notExponent;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    /**
     * Default constructor.
     *
     * @param element
     */
    public Cubed(Element element) {
        this.element = element;
        notExponent = !(element.getClass().getInterfaces()[0]
                .equals(Exponent.class));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
    @Override
    public Element getElement() {
        return element;
    }

    @Override
    public boolean isNotExponent() {
        return notExponent;
    }

    @Override
    public double evaluate() {
        return Math.pow(element.evaluate(), 3);
    }

    /**
     *
     * @return formatted string representation of value
     */
    @Override
    public String toString() {
        if (!(element.getClass().getInterfaces()[0].equals(Exponent.class))) {
            return element.toString() + SUPERSCRIPT;
        }
        return Equation.OPNBRKT + element.toString().trim() + Equation.CLSBRKT
                + SUPERSCRIPT;
    }
    // </editor-fold>

}
