package factory;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BaseExpression;
import model.Element;
import model.Expression;
import model.operator.Operator;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class ExpressionFactory {

    // <editor-fold defaultstate="collapsed" desc="Logger and related methods. Click on + sign to show.">
    /**
     * Logging tool.
     */
    private static final Logger LOGG = Logger.getLogger(ExpressionFactory.class
            .getName());

    /**
     * Configure the logger.
     */
    private static void startLogger() {
        LOGG.setLevel(Level.ALL);
        try {
            FileHandler saveLog = new FileHandler("ExpressionFactory.log", true);
            LOGG.addHandler(saveLog);
        } catch (IOException | SecurityException ex) {
            LOGG.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Logging routine.
     */
    private static void logExpressionCreation() {
        LOGG.finest("Creating Base Expression...");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    public ExpressionFactory() {
        startLogger();
    }
    // </editor-fold>

    public static Expression createExpression(Element first, Operator op,
            Element second) {
        logExpressionCreation();
        return new BaseExpression(first, op, second);
    }
}
