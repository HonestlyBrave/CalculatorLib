package model;

import java.text.DecimalFormat;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class CalcFormat {

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
        return FINE.format(number);
    }

}
