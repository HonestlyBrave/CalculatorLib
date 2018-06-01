package factory;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Scalar;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class ScalarFactory {

    // <editor-fold defaultstate="collapsed" desc="Logger and related methods. Click on + sign to show.">
    /**
     * Logging tool.
     */
    private static final Logger LOGG = Logger.getLogger(ScalarFactory.class
            .getName());

    /**
     * Configure the logger.
     */
    private static void startLogger() {
        LOGG.setLevel(Level.ALL);
        try {
            FileHandler saveLog = new FileHandler("ScalarFactory.log", true);
            LOGG.addHandler(saveLog);
        } catch (IOException | SecurityException ex) {
            LOGG.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Logging routine.
     */
    private static void logScalarCreation() {
        LOGG.finest("Creating Scalar...");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    public ScalarFactory() {
        startLogger();
    }
    // </editor-fold>

    public static Scalar createScalar(double value) {
        logScalarCreation();
        return new Scalar(value);
    }

}
