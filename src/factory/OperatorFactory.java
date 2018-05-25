package factory;

import model.operator.Add;
import model.operator.Divide;
import model.operator.Multiply;
import model.operator.Operator;
import model.operator.Subtract;
import view.View;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class OperatorFactory {

    public static Operator getOperator(int op, View view) {

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
}
