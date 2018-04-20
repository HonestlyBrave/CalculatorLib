package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.operator.*;

/**
 * This is the composite class which is composed of elements(Equations,
 * BaseExpressions and Scalars) and operators. These items are collected in
 * EQUATIONITEMS then simplified. The resulting last Element is then evaluated
 * for the solution..
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class Equation implements Element {

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * List of items within this Equation(parentheses).
     */
    private final List<Object> EQUATIONITEMS = new ArrayList<>();

    /**
     * Keep track of how many operators and elements.
     */
    private int opCount, eleCount;

    /**
     * Status of the first operand.
     */
    private boolean hasFirstOperand;

    /**
     * Status of the first operand.
     */
    private boolean hasOpenEquation;

    /**
     * Current input is to be squared.
     */
    private boolean squaredActive;

    /**
     * Current input is to be cubed.
     */
    private boolean cubedActive;

    /**
     * Current memory value.
     */
    private final Memory memory;

    /**
     * Current input.
     */
    private String input = "";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    /**
     * Default constructor which sets the various initial states of statuses.
     */
    public Equation() {
        this.hasFirstOperand = false;
        this.hasOpenEquation = false;
        this.squaredActive = false;
        this.cubedActive = false;
        this.opCount = 0;
        this.eleCount = 0;
        this.memory = Memory.getInstance();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
    /**
     * Add a Scalar element.
     *
     * @param scalar element to add to list
     */
    public final void addItem(Scalar scalar) {
        if (itemListIsEmpty()) {
            EQUATIONITEMS.add(scalar);
            this.eleCount++;
            this.hasFirstOperand = true;
            return;
        }

        if (!this.hasOpenEquation) {
            if (isSolvable()) {
                EQUATIONITEMS.add(new Multiply());
                this.opCount++;
                EQUATIONITEMS.add(scalar);
                this.eleCount++;
            } else {
                EQUATIONITEMS.add(scalar);
                this.eleCount++;
                this.hasFirstOperand = true;
            }

        } else if (lastIsEquation()) {
            getLastEquationItem().addItem(scalar);
        } else {
            // Fail at this point is a mystery. lol
            JOptionPane.showMessageDialog(null,
                    "There is a missing operator.",
                    "Missig Operator", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Add a Squared element.
     *
     * @param squared element to add to list
     */
    public final void addItem(Squared squared) {
        if (itemListIsEmpty()) {
            EQUATIONITEMS.add(squared);
            this.eleCount++;
            this.hasFirstOperand = true;
            this.squaredActive = false;
            return;
        }

        if (!this.hasOpenEquation) {
            if (isSolvable()) {
                EQUATIONITEMS.add(new Multiply());
                this.opCount++;
                EQUATIONITEMS.add(squared);
                this.eleCount++;
            } else {
                EQUATIONITEMS.add(squared);
                this.eleCount++;
            }
            this.hasFirstOperand = true;
            this.squaredActive = false;

        } else if (lastIsEquation()) {
            getLastEquationItem().addItem(squared);
        } else {
            // Fail at this point is a mystery. lol
            JOptionPane.showMessageDialog(null,
                    "There is a missing operator.",
                    "Missig Operator", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Add a Cubed element.
     *
     * @param cubed element to add to list
     */
    public final void addItem(Cubed cubed) {
        if (itemListIsEmpty()) {
            EQUATIONITEMS.add(cubed);
            this.eleCount++;
            this.hasFirstOperand = true;
            this.cubedActive = false;
            return;
        }

        if (!this.hasOpenEquation) {
            if (isSolvable()) {
                EQUATIONITEMS.add(new Multiply());
                this.opCount++;
                EQUATIONITEMS.add(cubed);
                this.eleCount++;
            } else {
                EQUATIONITEMS.add(cubed);
                this.eleCount++;
            }
            this.hasFirstOperand = true;
            this.cubedActive = false;

        } else if (lastIsEquation()) {
            getLastEquationItem().addItem(cubed);
        } else {
            // Fail at this point is a mystery. lol
            JOptionPane.showMessageDialog(null,
                    "There is a missing operator.",
                    "Missig Operator", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Add an operator.
     *
     * @param operator element to add to list
     */
    public final void addItem(Operator operator) {
        if (!this.hasOpenEquation) {
            if (isSolvable()) {
                EQUATIONITEMS.add(operator);
                this.opCount++;
            } else {
                JOptionPane.showMessageDialog(null, "There must be the correct "
                        + "amount of operators to operands for the primary "
                        + "equation and each subsequent parentheses.",
                        "Missing operators/operands", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            getLastEquationItem().addItem(operator);
        }
    }

    /**
     * Add an equation element.
     *
     * @param equation element to add to list
     */
    public final void addItem(Equation equation) {
        if (itemListIsEmpty()) {
            EQUATIONITEMS.add(equation);
            this.hasOpenEquation = true;
            return;
        }

        if (!this.hasOpenEquation) {
            if (isSolvable()) {
                EQUATIONITEMS.add(new Multiply());
                this.opCount++;
                EQUATIONITEMS.add(equation);
            } else {
                EQUATIONITEMS.add(equation);
            }
            this.hasOpenEquation = true;
        } else {
            getLastEquationItem().addItem(equation);
        }
    }

    /**
     * Add current input to this Equation's item list.
     *
     * @return boolean
     */
    public boolean addInput() {

        if (getInput().isEmpty()) {
            setInput("");
            return false;
        }

        if (squaredActive) {
            addItem(new Squared(parseInput()));
            setInput("");
            return true;
        }

        if (cubedActive) {
            addItem(new Cubed(parseInput()));
            setInput("");
            return true;
        }

        addItem(new Scalar(parseInput()));
        setInput("");
        return true;
    }

    /**
     * Check all Equations for an open Equation then close it.
     *
     */
    public void closeEquation() {
        if (isThereAnOpenEquation()
                && !getLastEquationItem().isThereAnOpenEquation()
                && getLastEquationItem().isSolvable()) {
            this.eleCount++;
            this.hasFirstOperand = true;
            this.hasOpenEquation = false;
        } else if (this.hasOpenEquation) {
            getLastEquationItem().closeEquation();
        } else {
            JOptionPane.showMessageDialog(null, "There must be the correct "
                    + "amount of operators to operands for the primary "
                    + "calculation and each subsequent parentheses.",
                    "Missing Operators/Elements", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     * @param value to be added to current memory value
     */
    public void addMemory(double value) {
        memory.add(value);
    }

    /**
     *
     * @param value to be subtracted from current memory value
     */
    public void subtractMemory(double value) {
        memory.subtract(value);
    }

    /**
     * If memory is zero return empty string.
     *
     * @return current memory value
     */
    public String recallMemory() {
        return memory.evaluate() == 0 ? "" : memory.toString();
    }

    /**
     * Set memory to zero.
     */
    public void clearMemory() {
        memory.clear();
    }

    @Override
    public double evaluate() {
        boolean check = true;

        while (check && EQUATIONITEMS.size() > 2) {
            check = calculateMultiplyDivide(EQUATIONITEMS);
        }

        check = true;

        while (check && EQUATIONITEMS.size() > 2) {
            check = calculateAddSubtract(EQUATIONITEMS);
        }

        return getLastElementItem().evaluate();
    }

    /**
     * Text representation of the last item in the Equation list.
     *
     * @return last element's evaluated result as a string
     */
    @Override
    public String toString() {
        return getLastElementItem().toString();
    }

    /**
     *
     * @return
     */
    public String getInput() {
        return input;
    }

    /**
     *
     * @param newInput
     */
    public void setInput(String newInput) {
        input = newInput;
    }

    /**
     *
     * @param newInput
     */
    public void updateInput(String newInput) {
        input += newInput;
    }

    /**
     * Set boolean for input will be Squared element.
     */
    public void activateSquare() {
        this.squaredActive = true;
    }

    /**
     * Set boolean for input will be Cubed element.
     */
    public void activateCube() {
        this.cubedActive = true;
    }

    /**
     * Check all Equations whether the active Equation is unsolvable.
     *
     * @return boolean
     */
    public boolean checkAll4Unsolvable() {
        return ((!isSolvable() && !isThereAnOpenEquation())
                || (isThereAnOpenEquation()
                && getLastEquationItem().checkAll4Unsolvable()));
    }

    /**
     * Check whether there is an open Equation.
     *
     * @return boolean
     */
    public boolean isThereAnOpenEquation() {
        return this.hasOpenEquation;
    }

    /**
     * Check whether last item of the active Equation is a closed Equation.
     *
     * @return boolean
     */
    public boolean nestedLastItemIsClosedEquation() {
        return (lastIsClosedEquation() || (isThereAnOpenEquation()
                && getLastEquationItem().nestedLastItemIsClosedEquation()));
    }

    /**
     * Check whether any Equation is empty.
     *
     * @return boolean
     */
    public boolean anyEquationEmpty() {
        return (itemListIsEmpty() || (isThereAnOpenEquation()
                && getLastEquationItem().anyEquationEmpty()));
    }

    /**
     * Check whether any nested Equation is empty.
     *
     * @return boolean
     */
    public boolean nestedEquationEmpty() {
        return (isThereAnOpenEquation() && getLastEquationItem()
                .anyEquationEmpty());
    }

    /**
     * Check whether last item of the active Equation is an Operator.
     *
     * @return boolean
     */
    public boolean nestedLastItemIsOperator() {
        return (lastIsOperator() || (isThereAnOpenEquation()
                && getLastEquationItem().nestedLastItemIsOperator()));
    }

    /**
     * Check whether last item of the active Equation is a Scalar.
     *
     * @return boolean
     */
    public boolean nestedLastItemIsScalar() {
        return (lastIsScalar() || (isThereAnOpenEquation()
                && getLastEquationItem().nestedLastItemIsScalar()));
    }

    /**
     * Check whether last item of the active Equation is a Square.
     *
     * @return boolean
     */
    public boolean nestedLastItemIsSquare() {
        return (lastIsSquare() || (isThereAnOpenEquation()
                && getLastEquationItem().nestedLastItemIsSquare()));
    }

    /**
     * Check whether last item of the active Equation is a Cube.
     *
     * @return boolean
     */
    public boolean nestedLastItemIsCube() {
        return (lastIsCube() || (isThereAnOpenEquation()
                && getLastEquationItem().nestedLastItemIsCube()));
    }

    /**
     * Check whether last item of the active Equation is an Exponent.
     *
     * @return boolean
     */
    public boolean nestedLastItemIsExponent() {
        return (lastIsExponent() || (isThereAnOpenEquation()
                && getLastEquationItem().nestedLastItemIsExponent()));
    }

    /**
     * True if the Equation's item list is empty.
     *
     * @return boolean
     */
    public boolean itemListIsEmpty() {
        return (EQUATIONITEMS.isEmpty());
    }

    /**
     * Reverse an action.
     */
    public void undoOperation() {
        if (itemListIsEmpty()) {
            JOptionPane.showMessageDialog(null, "Nothing to undo.",
                    "Missing Elements", JOptionPane.ERROR_MESSAGE);
        } else if (!lastIsEquation()) {
            EQUATIONITEMS.remove(getLastItem());
        } else if (lastIsClosedEquation()) {
            this.hasOpenEquation = true;
            getLastEquationItem().undoOperation();
        } else {
            getLastEquationItem().undoOperation();
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private methods. Click on + sign to show.">
    /**
     * Check this Equation's solvability with elements to operators ratio.
     *
     * @return boolean
     */
    private boolean isSolvable() {
        return this.eleCount == this.opCount + 1;
    }

    /**
     * Check if last item is a closed Equation.
     *
     * @return boolean
     */
    private boolean lastIsClosedEquation() {
        return (lastIsEquation() && !isThereAnOpenEquation());
    }

    /**
     *
     * @return Equation
     */
    private Equation getLastEquationItem() {
        return (Equation) getLastItem();
    }

    /**
     *
     * @return Element
     */
    private Element getLastElementItem() {
        return (Element) getLastItem();
    }

    /**
     * Check if last item is an Equation.
     *
     * @return boolean
     */
    private boolean lastIsEquation() {
        if (itemListIsEmpty()) {
            return false;
        }
        return (getLastItem().getClass().equals(Equation.class));
    }

    /**
     * Check if last item is an Operator.
     *
     * @return boolean
     */
    private boolean lastIsOperator() {
        if (itemListIsEmpty()) {
            return false;
        }
        return (getLastItem().getClass()
                .getInterfaces()[0].equals(Operator.class));
    }

    /**
     * Check if last item is a Scalar.
     *
     * @return boolean
     */
    private boolean lastIsScalar() {
        if (itemListIsEmpty()) {
            return false;
        }
        return (getLastItem().getClass().equals(Scalar.class));
    }

    /**
     * Check if last item is a Square.
     *
     * @return boolean
     */
    private boolean lastIsSquare() {
        if (itemListIsEmpty()) {
            return false;
        }
        return (getLastItem().getClass().equals(Squared.class));
    }

    /**
     * Check if last item is a Cube.
     *
     * @return boolean
     */
    private boolean lastIsCube() {
        if (itemListIsEmpty()) {
            return false;
        }
        return (getLastItem().getClass().equals(Cubed.class));
    }

    /**
     * Check if last item is an Exponent.
     *
     * @return boolean
     */
    private boolean lastIsExponent() {
        if (itemListIsEmpty()) {
            return false;
        }
        return (lastIsSquare() || lastIsCube());
    }

    /**
     * Return last object in an Equation's list.
     *
     * @return an Equation's last list item
     */
    private Object getLastItem() {
        if (itemListIsEmpty()) {
            return null;
        }
        return EQUATIONITEMS.get(EQUATIONITEMS.size() - 1);
    }

    /**
     * Parse the current input after removing commas.
     *
     * @return double
     */
    private double parseInput() {
        return Double.parseDouble(getInput().replaceAll(",", ""));
    }

    /**
     * Find multiply/divide operators to calculate.
     *
     * @param itemList list of elements and operators to simplify
     * @return boolean
     */
    private boolean calculateMultiplyDivide(List<Object> itemList) {
        for (Object obj : itemList) {
            if (obj.getClass().equals(Multiply.class)
                    || obj.getClass().equals(Divide.class)) {

                int index = itemList.indexOf(obj);

                calculate(index, itemList);

                return true;
            }
        }
        return false;
    }

    /**
     * Find add/subtract operators to calculate.
     *
     * @param itemList list of elements and operators to simplify
     * @return boolean
     */
    private boolean calculateAddSubtract(List<Object> itemList) {
        for (Object obj : itemList) {
            if (obj.getClass().equals(Add.class)
                    || obj.getClass().equals(Subtract.class)) {

                int index = itemList.indexOf(obj);

                calculate(index, itemList);

                return true;
            }
        }
        return false;
    }

    /**
     * Create BaseExpression elements then update the List of objects.
     *
     * @param index
     * @param list
     */
    private void calculate(int index, List<Object> list) {
        BaseExpression tmp = new BaseExpression((Element) list
                .get(index - 1), (Operator) list.get(index),
                (Element) list.get(index + 1));

        list.add(index - 1, tmp);
        list.remove(index);
        list.remove(index);
        list.remove(index);
    }
    // </editor-fold>
}
