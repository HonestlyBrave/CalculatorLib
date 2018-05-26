package factory;

import model.Scalar;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class ScalarFactory {

    public static Scalar createScalar(double value) {
        return new Scalar(value);
    }

}
