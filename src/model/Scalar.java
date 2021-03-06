package model;

/**
 * Leaf class of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Scalar implements Element {

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * Number to be evaluated.
     */
    private final double number;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    /**
     * Default constructor.
     *
     * @param number to be used as value
     */
    public Scalar(double number) {
        this.number = number;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
    @Override
    public double evaluate() {
        return number;
    }

    /**
     *
     * @return formatted string representation of value
     */
    @Override
    public String toString() {
        return CalcFormat.format(number);
    }
    // </editor-fold>
}
