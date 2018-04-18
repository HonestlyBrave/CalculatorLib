package model;

import java.text.DecimalFormat;

/**
 * Singleton class to manage memory.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Memory implements Element {

    // <editor-fold defaultstate="collapsed" desc="Singleton. Click on + sign to show.">
    /**
     * Singleton INSTANCE of Memory class.
     */
    private static final Memory INSTANCE = getInstance();

    /**
     * Singleton design pattern.
     *
     * @return INSTANCE
     */
    public static Memory getInstance() {
        return INSTANCE == null ? new Memory() : INSTANCE;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * Current value stored in memory.
     */
    private double currentValue;

    /**
     * Use commas as separator and eliminate extra zeros after decimal.
     */
    private final DecimalFormat FINE;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private constructor. Click on + sign to show.">
    /**
     * Private singleton constructor.
     *
     */
    private Memory() {
        this.currentValue = 0;
        this.FINE = new DecimalFormat("");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
    /**
     *
     * @param value to add to currentValue
     */
    public void add(double value) {
        this.currentValue += value;
    }

    /**
     *
     * @param value to subtract from currentValue
     */
    public void subtract(double value) {
        this.currentValue -= value;
    }

    /**
     * Set currentValue to zero.
     */
    public void clear() {
        this.currentValue = 0;
    }

    @Override
    public double evaluate() {
        return currentValue;
    }

    /**
     *
     * @return value as string
     */
    public String toString() {
        return FINE.format(currentValue);
    }
    // </editor-fold>
}
