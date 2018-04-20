package view;

import model.Facade;

/**
 * View interface that needs to be implemented to utilize this library.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public interface View {

    /**
     * Make the view visible. This method is for applications.
     */
    public void setVisible();

    /**
     * Set the initial focus in the view. This method can be left empty.
     */
    public void setFocus();

    /**
     * Get the view's user display control text. This method is necessary.
     *
     * @return display text
     */
    public String getDisplay();

    /**
     * Set the view's user display control text. This method is necessary.
     *
     * @param text to replace display text
     */
    public void setDisplay(String text);

    /**
     * Update the view's user display control text. This method is necessary.
     *
     * @param text to add to display text
     */
    public void updateDisplay(String text);

    /**
     * Remove last character input. Undo is not fully implemented.
     */
    default public void undoDisplay() {
        String text = getDisplay();
        int textLen = text.length();

        // Set empty if empty or get all characters -1(last entry).
        String newText = text.isEmpty() ? "" : text.substring(0, textLen - 1);

        setDisplay(newText);
    }

    /**
     * Set the Facade's view. Be sure to set the view once only. This method is
     * necessary.
     */
    default public void setCalculatorViewer() {
        Facade.setView(this);
    }

}
