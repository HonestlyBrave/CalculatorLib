package factory;

import model.operator.*;
import view.View;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class OperatorFactory {

    public static Operator createOperator(int op, View view) {

        switch (op) {
            case 1:
                view.updateDisplay(Add.OPERATOR);
                return new Add();
            case 2:
                view.updateDisplay(Subtract.OPERATOR);
                return new Subtract();
            case 3:
                view.updateDisplay(Multiply.OPERATOR);
                return new Multiply();
            case 4:
                view.updateDisplay(Divide.OPERATOR);
                return new Divide();
            default:
                return null;
        }

    }

    public static Operator createOperator(int op) {

        switch (op) {
            case 1:
                return new Add();
            case 2:
                return new Subtract();
            case 3:
                return new Multiply();
            case 4:
                return new Divide();
            default:
                return null;
        }

    }

}
