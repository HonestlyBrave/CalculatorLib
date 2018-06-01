package model;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class CalcFormat {

    // <editor-fold defaultstate="collapsed" desc="Logger and related methods. Click on + sign to show.">
    /**
     * Logging tool.
     */
    private static final Logger LOGG = Logger.getLogger(CalcFormat.class
            .getName());

    /**
     * Configure the logger.
     */
    private static void startLogger() {
        LOGG.setLevel(Level.ALL);
        try {
            FileHandler saveLog = new FileHandler("CalcFormat.log", true);
            LOGG.addHandler(saveLog);
        } catch (IOException | SecurityException ex) {
            LOGG.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Logging routine.
     */
    private static void logFormat(double value) {
        LOGG.log(Level.FINEST, "Formatting double to string...{0}", value);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor. Click on + sign to show.">
    public CalcFormat() {
        startLogger();
    }
    // </editor-fold>

    /**
     * Use commas as separator and eliminate extra zeros after decimal.
     */
    private static final DecimalFormat FINE = new DecimalFormat("");

    /**
     *
     * @param number
     * @return number as string delimited with commas.
     */
    public static String format(double number) {
        logFormat(number);
        return FINE.format(number);
    }

}
