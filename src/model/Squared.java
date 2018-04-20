package model;

import java.text.DecimalFormat;

/**
 * Leaf class of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Squared implements Element {

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * Number to be evaluated.
     */
    private final double number;

    /**
     * Use commas as separator and eliminate extra zeros after decimal.
     */
    private final DecimalFormat FINE = new DecimalFormat("");
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    /**
     * Default constructor.
     *
     * @param number to be squared
     */
    public Squared(double number) {
        this.number = number;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
    @Override
    public double evaluate() {
        return Math.pow(number, 2);
    }

    /**
     *
     * @return formatted string representation of value
     */
    @Override
    public String toString() {
        return FINE.format(number) + "²";
    }
    // </editor-fold>

}
