package model;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Cubed implements Element {

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * Number.
     */
    private double number;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    /**
     * Default constructor.
     *
     * @param number value of this Scalar object
     */
    public Cubed(double number) {
        this.number = number;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
    /**
     *
     * @return number
     */
    @Override
    public double evaluate() {
        return Math.pow(number, 3);
    }

    /**
     *
     * @return formatted string representation of value
     */
    @Override
    public String toString() {
        return number + "³";
    }
    // </editor-fold>

}
