package factory;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.operator.*;
import view.View;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class OperatorFactory {

    // <editor-fold defaultstate="collapsed" desc="Logger and related methods. Click on + sign to show.">
    /**
     * Logging tool.
     */
    private static final Logger LOGG = Logger.getLogger(OperatorFactory.class
            .getName());

    /**
     * Configure the logger.
     */
    private static void startLogger() {
        LOGG.setLevel(Level.ALL);
        try {
            FileHandler saveLog = new FileHandler("OperatorFactory.log", true);
            LOGG.addHandler(saveLog);
        } catch (IOException | SecurityException ex) {
            LOGG.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Logging routine.
     *
     * @param op
     */
    private static void logOperatorCreation(String op) {
        LOGG.log(Level.INFO, "Creating {0} Operator...", op);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    public OperatorFactory() {
        startLogger();
    }
    // </editor-fold>

    public static Operator createOperator(int op, View view) {

        switch (op) {
            case 1:
                logOperatorCreation("Add");
                view.updateDisplay(Add.OPERATOR);
                return new Add();
            case 2:
                logOperatorCreation("Subtract");
                view.updateDisplay(Subtract.OPERATOR);
                return new Subtract();
            case 3:
                logOperatorCreation("Multiply");
                view.updateDisplay(Multiply.OPERATOR);
                return new Multiply();
            case 4:
                logOperatorCreation("Divide");
                view.updateDisplay(Divide.OPERATOR);
                return new Divide();
            default:
                return null;
        }

    }

    public static Operator createOperator(int op) {

        switch (op) {
            case 1:
                logOperatorCreation("Add");
                return new Add();
            case 2:
                logOperatorCreation("Subtract");
                return new Subtract();
            case 3:
                logOperatorCreation("Multiply");
                return new Multiply();
            case 4:
                logOperatorCreation("Divide");
                return new Divide();
            default:
                return null;
        }

    }

}
