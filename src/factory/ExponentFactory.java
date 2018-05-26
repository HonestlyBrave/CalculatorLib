package factory;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Element;
import model.exponent.*;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class ExponentFactory {

    // <editor-fold defaultstate="collapsed" desc="Logger and related methods. Click on + sign to show.">
    /**
     * Logging tool.
     */
    private static final Logger LOGG = Logger.getLogger(ExponentFactory.class
            .getName());

    /**
     * Configure the logger.
     */
    private static void startLogger() {
        LOGG.setLevel(Level.ALL);
        try {
            FileHandler saveLog = new FileHandler("ExponentFactory.log", true);
            LOGG.addHandler(saveLog);
        } catch (IOException | SecurityException ex) {
            LOGG.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Logging routine.
     */
    private static void logExponentCreation() {
        LOGG.info("Creating Exponent...");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    public ExponentFactory() {
        startLogger();
    }
    // </editor-fold>

    public static Exponent createExponent(int type, Element value) {

        logExponentCreation();
        switch (type) {
            case 2:
                return new Squared(value);
            case 3:
                return new Cubed(value);
            default:
                return null;
        }

    }

}
