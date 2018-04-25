package model;

import java.text.DecimalFormat;

/**
 * Leaf class of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Cubed implements Element {

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * Element to be evaluated.
     */
    private final Element element;

    /**
     * Use commas as separator and eliminate extra zeros after decimal.
     */
    private final DecimalFormat FINE = new DecimalFormat("");
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    /**
     * Default constructor.
     *
     * @param element
     */
    public Cubed(Element element) {
        this.element = element;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
    /**
     *
     * @return current Element
     */
    public Element getElement() {
        return element;
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
        if (element instanceof Scalar || element instanceof Equation) {
            return element.toString() + "³";
        }
        return "(" + element.toString() + ")" + "³";
    }
    // </editor-fold>

}
