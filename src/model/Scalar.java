package model;

import java.text.DecimalFormat;

/**
 * Leaf class of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Scalar implements Element {

    /**
     * Number.
     */
    private double number;

    /**
     * Use commas as separator and eliminate extra zeros after decimal.
     */
    private final DecimalFormat FINE = new DecimalFormat("");

    /**
     * Default constructor.
     *
     * @param number value of this Scalar object
     */
    public Scalar(double number) {
        this.number = number;
    }

    /**
     *
     * @return number
     */
    public double getNumber() {
        return number;
    }

    /**
     *
     * @param number value of this Scalar object
     */
    public void setNumber(double number) {
        this.number = number;
    }

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
        return FINE.format(number);
    }
}
