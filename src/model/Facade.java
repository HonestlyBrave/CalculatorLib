package model;

import command.Command;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.swing.JOptionPane;
import model.exponent.Cubed;
import model.exponent.Squared;
import model.operator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.View;

/**
 * Facade design pattern to compliment Command design pattern. The Facade is
 * also responsible for adhering to math rules.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("facade")
public class Facade {

    // <editor-fold defaultstate="collapsed" desc="View attribute and related methods. Click on + sign to show.">
    /**
     * View Class object that is set by the implementor.
     */
    @Autowired
    private static View view;

    /**
     * Use this method to set the view.
     *
     * @param aView
     */
    public static void setView(View aView) {
        view = aView;
    }

    /**
     * Synchronize machine display with user display.
     */
    private static void synchMachineDisplay() {
        displayText = view.getDisplay();
    }

    /**
     * Set user display.
     *
     * @param text
     */
    private static void setUserDisplay(String text) {
        view.setDisplay(text);
    }

    /**
     * Update user display.
     *
     * @param text
     */
    private static void updateUserDisplay(String text) {
        view.updateDisplay(text);
    }

    /**
     * Undo user display.
     */
    private static void undoUserDisplay() {
        view.undoDisplay();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * Main Equation for calculation using elements and operators. Elements are
     * base expressions, equations(parentheses), scalars and a singleton memory.
     */
    private static Equation PRIMARY = new Equation(false);

    /**
     * Machine equivalent of actual display.
     */
    private static String displayText = "";

    /**
     * Machine reference of output to replace/update display.
     */
    private static String output = "";

    /**
     * Machine reference of last answer.
     */
    private static String answer = "";

    /**
     * Current input.
     */
    private static final Input INPUT = Input.getInstance();

    /**
     * Stack(LIFO) of executed commands.
     */
    private static final Deque<Command> COMANDS = new ArrayDeque();

    /**
     * Stack(LIFO) of input, displayText, output, answers, operations, operands
     * and item lists.
     */
    private static final Deque<Object> UNDOCOMANDS = new ArrayDeque<>();

    /**
     * Use commas as separator and eliminate extra zeros after decimal.
     */
    private static final DecimalFormat FINE = new DecimalFormat("");
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Core public methods. Click on + sign to show.">
    /**
     * Add a value to memory.
     */
    public static void addMemory() {
        // Save state for undo
        UNDOCOMANDS.push(PRIMARY);

        if (answerNotEmpty()) {
            INPUT.setInput(removeCommas(answer));
        }

        if (PRIMARY.nestedLastItemIsExponent()) {
            PRIMARY.addMemory(PRIMARY.getLastNestedElementItem().evaluate());
            return;
        }

        if (validateNewMemVal()) {
            PRIMARY.addMemory(parseCommas(INPUT.getInput()));
        }
    }

    /**
     * Subtract a value from memory.
     */
    public static void subtractMemory() {
        // Save state for undo
        UNDOCOMANDS.push(PRIMARY);

        if (answerNotEmpty()) {
            INPUT.setInput(removeCommas(answer));
        }

        if (PRIMARY.nestedLastItemIsExponent()) {
            PRIMARY.addMemory(PRIMARY.getLastNestedElementItem().evaluate());
            return;
        }

        if (validateNewMemVal()) {
            PRIMARY.subtractMemory(parseCommas(INPUT.getInput()));
        }
    }

    /**
     * Retrieve a value from memory, then update or replace display.
     */
    public static void recallMemory() {
        // Save state for undo
        UNDOCOMANDS.push(PRIMARY);

        if (PRIMARY.recallMemory().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "There is no memory value or it is zero.",
                    "No memory value", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If there is new input.
        if (!INPUT.isEmpty()) {
            if (newInputAfterClosedEquation()) {
                addMultiplySignAfterLastEquation();
            }
            PRIMARY.addInput();
        }

        // Set memory value to input and output.
        setMachineInputOutput(PRIMARY.recallMemory());

        // Start of a new calculation.
        if (PRIMARY.itemListIsEmpty()) {
            setUserDisplay(output);
            return;
        }

        // Empty parentheses or operator.
        if (PRIMARY.anyEquationEmpty() || PRIMARY.nestedLastItemIsOperator()
                || PRIMARY.nestedLastItemIsClosedEquation()) {
            updateUserDisplay(output);
        } else {// Else there is a pre-existing scalar.
            updateUserDisplay(" ˣ " + output);
        }
    }

    /**
     * Clear memory of any value including zero.
     */
    public static void clearMemory() {
        // Save state for undo
        UNDOCOMANDS.push(PRIMARY);

        PRIMARY.clearMemory();
    }

    /**
     * Reinitialize calculator.
     */
    public static void clear() {
        // Save state for undo
        UNDOCOMANDS.push(PRIMARY);

        synchMachineDisplay();

        // Clear display and input.
        displayText = "";
        INPUT.setInput("");
        output = "";
        answer = "";

        // New primary calculation.
        PRIMARY = new Equation(false);
        setUserDisplay("");
    }

    /**
     * Check for valid value then addItem operator to ArrayList.
     *
     * @param op 1 = add, 2 = subtract, 3 = multiply, 4 = operator
     * @return boolean
     */
    public static boolean operator(int op) {
        if (operatorNotAllowed()) {
            return false;
        }
        // Save state for undo
        UNDOCOMANDS.push(PRIMARY);

        synchMachineDisplay();

        if (answerNotEmpty()) {
            INPUT.setInput(removeCommas(answer));
        }

        if (verifyNewValueOrSpecificElement()) {
            if (newInputAfterClosedEquation()) {
                addMultiplySignAfterLastEquation();
            }
            if (newInputAfterExponent()) {
                addMultiplySignAfterLastExponent();
            }

            if (!PRIMARY.addInput()
                    && !PRIMARY.nestedLastItemIsClosedEquation()
                    && !PRIMARY.nestedLastItemIsExponent()) {
                JOptionPane.showMessageDialog(null,
                        "You must enter a valid operand "
                        + "before choosing an operator.",
                        "Missing operand", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        switch (op) {
            case 1:
                PRIMARY.addItem(new Add());
                updateUserDisplay(" + ");
                break;
            case 2:
                PRIMARY.addItem(new Subtract());
                updateUserDisplay(" - ");
                break;
            case 3:
                PRIMARY.addItem(new Multiply());
                updateUserDisplay(" ˣ ");
                break;
            default:
                PRIMARY.addItem(new Divide());
                updateUserDisplay(" ÷ ");
                break;
        }
        return true;
    }

    /**
     * Check for valid value then addItem square or cube to ArrayList.
     *
     * @param isSquared true for Squared element and false for Cubed element
     * @return boolean
     */
    public static boolean exponent(boolean isSquared) {
        // Save state for undo
        UNDOCOMANDS.push(PRIMARY);

        synchMachineDisplay();

        if (answerNotEmpty()) {
            INPUT.setInput(removeCommas(answer));
        }

        if (validateNewVal()) {

            if (PRIMARY.nestedLastItemIsClosedEquation()) {
                addMultiplySignAfterLastEquation();
            }
            if (PRIMARY.nestedLastItemIsExponent()) {
                addMultiplySignAfterLastExponent();
            }

            isCubedOrSquared(isSquared);
            PRIMARY.addInput();

        } else if (PRIMARY.nestedLastItemIsClosedEquation()) {

            isCubedOrSquared(isSquared);
            addElementExponent(isSquared);

        } else if (PRIMARY.nestedLastItemIsExponent()) {

            isCubedOrSquared(isSquared);
            addElementExponent(isSquared);

        } else {
            return false;
        }

        synchMachineDisplay();

        if (isSquared) {
            if (!displayText.trim().endsWith("²")) {
                updateUserDisplay(output);
            }
        } else {
            if (!displayText.trim().endsWith("³")) {
                updateUserDisplay(output);
            }
        }
        return true;
    }

    /**
     * Check for valid Scalar then add new Equation to primary Equation.
     */
    public static void openParentheses() {
        updateUserDisplay("(");
        // Save state for undo
        UNDOCOMANDS.push(PRIMARY);

        synchMachineDisplay();

        if (answerNotEmpty()) {
            INPUT.setInput(removeCommas(answer));
        }

        if (verifyNewValueOrSpecificElement()) {
            if (newInputAfterClosedEquation()) {
                addMultiplySignAfterLastEquation();
            }
            if (newInputAfterExponent()) {
                addMultiplySignAfterLastExponent();
            }

            PRIMARY.addInput();
            addMultiplySignAfterInput();
        }

        PRIMARY.addItem(new Equation());
    }

    /**
     * Check for valid Scalar then close equation.
     *
     * @return boolean
     */
    public static boolean closeParentheses() {
        if (cannotCloseParaNow()) {
            return false;
        }
        // Save state for undo
        UNDOCOMANDS.push(PRIMARY);

        synchMachineDisplay();

        if (validateNewVal()) {
            if (newInputAfterClosedEquation()) {
                addMultiplySignAfterLastEquation();
            }

            PRIMARY.addInput();
        }

        PRIMARY.closeEquation();
        updateUserDisplay(")");
        return true;
    }

    /**
     * Check for valid value then solve equation.
     */
    public static void solve() {
        // Save state for undo
        UNDOCOMANDS.push(PRIMARY);

        synchMachineDisplay();

        if (!answer.isEmpty() && PRIMARY.itemListIsEmpty()
                && INPUT.isEmpty()) {
            INPUT.setInput(removeCommas(answer));
        }

        // Verify input exist.
        if (validateNewVal()) {
            PRIMARY.addInput();
        }

        // Verify if any Equation(parentheses) is not valid.
        if (PRIMARY.checkAll4Unsolvable()) {
            JOptionPane.showMessageDialog(null,
                    "The equation can not be solved due to an unclosed "
                    + "parentheses or missing operand/operator.",
                    "Missing element or operator", JOptionPane.ERROR_MESSAGE);

            PRIMARY = (Equation) UNDOCOMANDS.pop();
            return;
        }
        // Evaluate the last element.
        answer = FINE.format(PRIMARY.evaluate());

        // Update display with complete expression and result.
        setUserDisplay(PRIMARY.toString() + " = " + answer);

        // Clear main equation.
        PRIMARY = new Equation(false);
        INPUT.setInput("");
    }

    /**
     * Literal user input between operations.
     *
     * @param latestInput literal user input
     */
    public static void updateInput(String latestInput) {
        INPUT.updateInput(latestInput);
        if (RemoveAnswerFromDisplay()) {
            setUserDisplay("");
        }
        updateUserDisplay(latestInput);
    }

    /**
     * Push a command to the queue.
     *
     * @param aComand save a command for undo operations
     */
    public static void pushComand(Command aComand) {
        COMANDS.push(aComand);
    }

    /**
     * Retrieve an executed command and execute its undo method.
     */
    public static void undo() {
        if (!getComands().isEmpty()) {
            popComand().undo();
        } else {
            JOptionPane.showMessageDialog(null, "Nothing to undo.",
                    "Invalid Operation", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Remove the last character from input and display.
     */
    public static void undoDisplay() {
        String current = INPUT.getInput();
        int currentLen = current.length();

        String undone = current.isEmpty() ? ""
                : current.substring(0, currentLen - 1);

        INPUT.setInput(undone);
        undoUserDisplay();
    }

    /**
     * Remove the last operator and it's accompanying operand.
     */
    public static void undoOperation() {
        PRIMARY = (Equation) UNDOCOMANDS.pop();
        setUserDisplay(displayText);
    }

    /**
     * Undo a solve operation.
     */
    public static void undoSolve() {
        PRIMARY = (Equation) UNDOCOMANDS.pop();
        setUserDisplay(displayText);
    }

    /**
     * Undo a clear operation.
     */
    public static void undoClear() {
        PRIMARY = (Equation) UNDOCOMANDS.pop();
        setUserDisplay(displayText);
    }

    /**
     * Undo a memory operation.
     */
    public static void undoAddMem() {
        PRIMARY = (Equation) UNDOCOMANDS.pop();
        setUserDisplay(displayText);
    }

    /**
     * Undo a memory operation.
     */
    public static void undoSubtractMem() {
        PRIMARY = (Equation) UNDOCOMANDS.pop();
        setUserDisplay(displayText);
    }

    /**
     * Undo a memory operation.
     */
    public static void undoRecallMem() {
        PRIMARY = (Equation) UNDOCOMANDS.pop();
        setUserDisplay(displayText);
    }

    /**
     * Undo a memory operation.
     */
    public static void undoClearMem() {
        PRIMARY = (Equation) UNDOCOMANDS.pop();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Core private methods. Click on + sign to show.">
    /**
     * Trim display data as needed to return value only.
     *
     * @return value substring of user display
     */
    private static String getCleanString() {
        synchMachineDisplay();

        String cleanString;

        // Find index of space before value.
        int index = displayText.lastIndexOf(" ");
        // Take everything after index.
        cleanString = displayText.substring(index + 1);
        // Remove open bracket if exists then return.
        return cleanString.replace("(", "");
    }

    /**
     * Validate user/machine input.
     *
     * @return boolean
     */
    private static boolean validateNewVal() {
        return (!INPUT.isEmpty());
    }

    /**
     * Validate user/machine memory input.
     *
     * @return boolean
     */
    private static boolean validateNewMemVal() {
        if (!INPUT.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "There is no value to use.",
                    "Missing value", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Validate a new value or whether the previous item was an Equation or
     * Scalar. The exception for no Scalar value before an operator.
     *
     * @return boolean
     */
    private static boolean verifyNewValueOrSpecificElement() {
        return (validateNewVal() || PRIMARY.nestedLastItemIsClosedEquation()
                || PRIMARY.nestedLastItemIsScalar()
                || PRIMARY.nestedLastItemIsExponent());
    }

    /**
     * True if there is new input and the last item is a closed Equation.
     *
     * @return boolean
     */
    private static boolean newInputAfterClosedEquation() {
        return (validateNewVal() && PRIMARY.nestedLastItemIsClosedEquation());
    }

    /**
     * True if there is new input and the last item is an Exponent.
     *
     * @return boolean
     */
    private static boolean newInputAfterExponent() {
        return (validateNewVal() && PRIMARY.nestedLastItemIsExponent());
    }

    /**
     * Start a new calculation with current answer.
     *
     * @return boolean
     */
    private static boolean answerNotEmpty() {
        return (!answer.isEmpty() && PRIMARY.itemListIsEmpty()
                && INPUT.isEmpty());
    }

    /**
     * True if both attributes are empty.
     *
     * @return boolean
     */
    private static boolean bothInputAnswerEmpty() {
        return (INPUT.isEmpty() && answer.isEmpty());
    }

    /**
     * Is Equation list empty, while answer is not and input has no more than
     * one character.
     *
     * @return boolean
     */
    private static boolean RemoveAnswerFromDisplay() {
        return (!answer.isEmpty() && PRIMARY.itemListIsEmpty()
                && INPUT.getInput().length() == 1);
    }

    /**
     * Check current equation for illegal state before adding new operator.
     *
     * @return boolean
     */
    private static boolean operatorNotAllowed() {
        return ((PRIMARY.itemListIsEmpty() && bothInputAnswerEmpty())
                || (PRIMARY.nestedEquationEmpty()
                && INPUT.isEmpty())
                || (PRIMARY.nestedLastItemIsOperator()
                && INPUT.isEmpty()));
    }

    /**
     * Check all Equations illegal state before closing.
     *
     * @return boolean
     */
    private static boolean cannotCloseParaNow() {
        return (PRIMARY.itemListIsEmpty()
                || !PRIMARY.isThereAnOpenEquation()
                || (PRIMARY.nestedLastItemIsOperator() && INPUT.isEmpty())
                || (INPUT.isEmpty()
                && PRIMARY.nestedEquationEmpty()));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private methods. Click on + sign to show.">
    /**
     * Set machine view and user/machine input.
     *
     * @param text for machine display and input
     */
    private static void setMachineInputOutput(String text) {
        output = text;
        INPUT.setInput(removeCommas(text));
    }

    /**
     * Get the executed commands collection.
     *
     * @return list of commands for undo operations
     */
    private static Deque<Command> getComands() {
        return COMANDS;
    }

    /**
     * Pop a command from the queue.
     *
     * @return a command for undo operations
     */
    private static Command popComand() {
        return COMANDS.pop();
    }

    /**
     * Add literal "ˣ" to user display before parentheses.
     */
    private static void addMultiplySignAfterInput() {
        synchMachineDisplay();
        int tmp = displayText.lastIndexOf("(");
        displayText = displayText.substring(0, tmp);
        displayText = displayText.concat(" ˣ (");
        setUserDisplay(displayText);
    }

    /**
     * Add literal "ˣ" to user display after parentheses.
     */
    private static void addMultiplySignAfterLastEquation() {
        synchMachineDisplay();
        int tmp = displayText.lastIndexOf(")");
        String newinput = displayText.substring(tmp + 1);
        displayText = displayText.substring(0, tmp);
        displayText = displayText.concat(") ˣ " + newinput);
        setUserDisplay(displayText);
    }

    /**
     * Add literal "ˣ" to user display after exponent.
     */
    private static void addMultiplySignAfterLastExponent() {
        synchMachineDisplay();

        // Select last existing superscript.
        int tmp2 = displayText.lastIndexOf("²");
        int tmp3 = displayText.lastIndexOf("³");
        int tmp = tmp2 > tmp3 ? tmp2 : tmp3;

        // If superscript exist,
        if (tmp != -1) {
            // separate newest input from display text.
            String newinput = displayText.substring(tmp + 1);
            displayText = displayText.substring(0, tmp + 1);
            // Concatenate with multiply sign in between input and display text.
            setUserDisplay(displayText.concat(" ˣ " + newinput));
        }
    }

    /**
     *
     * @param number formatted to allow commas
     * @return number without commas
     */
    private static String removeCommas(String number) {
        return number.replaceAll(",", "");
    }

    /**
     * Two methods that allow Double class to parse formatted strings.
     *
     * @param number formatted to allow commas
     * @return double
     */
    private static double parseCommas(String number) {
        return Double.parseDouble(removeCommas(number));
    }

    /**
     * Activate correct flag and set the output accordingly.
     *
     * @param isSquared
     */
    private static void isCubedOrSquared(boolean isSquared) {
        if (isSquared) {
            PRIMARY.activateSquare();
            output = "²";
        } else {
            PRIMARY.activateCube();
            output = "³";
        }
    }

    /**
     * Add correct element to Primary equation.
     *
     * @param isSquared
     */
    private static void addElementExponent(boolean isSquared) {
        Element tmpElement = PRIMARY.getLastNestedElementItem();

        PRIMARY.removeLastElement();
        if (isSquared) {
            PRIMARY.addItem(new Squared(tmpElement));
        } else {
            PRIMARY.addItem(new Cubed(tmpElement));
        }
        synchMachineDisplay();
        view.setDisplay(PRIMARY.toString());
    }
    // </editor-fold>

}
