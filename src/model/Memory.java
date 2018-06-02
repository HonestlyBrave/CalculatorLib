package model;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class to manage memory.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Memory implements Element {

    // <editor-fold defaultstate="collapsed" desc="Logger and related methods. Click on + sign to show.">
    /**
     * Logging tool.
     */
    private static final Logger LOGG = Logger.getLogger(Memory.class
            .getName());

    /**
     * Configure the logger.
     */
    private static void startLogger() {
        LOGG.setLevel(Level.ALL);
        try {
            FileHandler saveLog = new FileHandler("Memory.log", true);
            LOGG.addHandler(saveLog);
        } catch (IOException | SecurityException ex) {
            LOGG.log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>

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
        LOGG.finest("Calling the Memory instance.");
        return INSTANCE == null ? new Memory() : INSTANCE;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * Current value stored in memory.
     */
    private double currentValue;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private constructor. Click on + sign to show.">
    /**
     * Private singleton constructor.
     *
     */
    private Memory() {
        startLogger();
        this.currentValue = 0;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
    /**
     *
     * @param value to add to currentValue
     */
    public void add(double value) {
        this.currentValue += value;
        LOGG.log(Level.INFO, "Current Input or its value added to Memory. "
                + "Value added : {0}", value);
    }

    /**
     *
     * @param value to subtract from currentValue
     */
    public void subtract(double value) {
        this.currentValue -= value;
        LOGG.log(Level.INFO,
                "Current Input or its value subtracted from Memory. "
                + "Value subtracted : {0}", value);
    }

    /**
     * Set currentValue to zero.
     */
    public void clear() {
        this.currentValue = 0;
        LOGG.info("Memory cleared.");
    }

    @Override
    public double evaluate() {
        LOGG.finest("Evaluating Memory...");
        return currentValue;
    }

    /**
     *
     * @return value as string
     */
    public String toString() {
        return CalcFormat.format(currentValue);
    }
    // </editor-fold>
}
