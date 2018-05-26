package factory;

import model.Equation;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class EquationFactory {

    public static Equation createEquation() {
        return new Equation();
    }

    public static Equation createEquation(boolean primary) {
        return new Equation(primary);
    }

}
