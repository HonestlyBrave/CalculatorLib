package view;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public interface View {

    /**
     * Get the view's user display control text.
     *
     * @return display text
     */
    public String getDisplay();

    /**
     * Set the view's user display control text.
     *
     * @param text to replace display text
     */
    public void setDisplay(String text);

    /**
     * Update the view's user display control text.
     *
     * @param text to add to display text
     */
    public void updateDisplay(String text);

    /**
     * Remove last character input.
     */
    public void undoDisplay();

}
