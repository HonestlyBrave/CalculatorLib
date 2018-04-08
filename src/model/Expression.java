package model;

/**
 * Interface that distinguishes expressions from other elements of the math
 * composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public interface Expression extends Element {

    /**
     * Add first operand.
     *
     * @param element of composite
     */
    public void addFirst(Element element);

    /**
     * Add second operand.
     *
     * @param element of composite
     */
    public void addSecond(Element element);
}
