package model;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class to manage Input.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Input {

    // <editor-fold defaultstate="collapsed" desc="Logger and related methods. Click on + sign to show.">
    /**
     * Logging tool.
     */
    private static final Logger LOGG = Logger.getLogger(Input.class.getName());

    /**
     * Configure the logger.
     */
    private static void startLogger() {
        LOGG.setLevel(Level.ALL);
        try {
            FileHandler saveLog = new FileHandler("Input.log", true);
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
    private static final Input INSTANCE = getInstance();

    /**
     * Singleton design pattern.
     *
     * @return INSTANCE
     */
    public static Input getInstance() {
        return INSTANCE == null ? new Input() : INSTANCE;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * Current value stored in memory.
     */
    private String currentInput;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private constructor. Click on + sign to show.">
    /**
     * Private singleton constructor.
     *
     */
    private Input() {
        startLogger();
        this.currentInput = "";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
    /**
     *
     * @return currentInput
     */
    public String getInput() {
        return currentInput;
    }

    /**
     * Set currentInput.
     *
     * @param newInput
     */
    public void setInput(String newInput) {
        currentInput = newInput;
        if (newInput.equals("")) {
            LOGG.finest("Input reset to empty string.");
        }
    }

    /**
     * Update currentInput.
     *
     * @param newInput
     */
    public void updateInput(String newInput) {
        currentInput += newInput;
    }

    /**
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return currentInput.equals("") || currentInput.equals(".");
    }

    /**
     *
     * @return currentInput
     */
    public String toString() {
        return currentInput;
    }
    // </editor-fold>
}
