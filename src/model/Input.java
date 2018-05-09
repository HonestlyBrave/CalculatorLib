package model;

/**
 * Singleton class to manage Input.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Input {

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
