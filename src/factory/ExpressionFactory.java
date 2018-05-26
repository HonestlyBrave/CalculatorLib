package factory;

import model.BaseExpression;
import model.Element;
import model.Expression;
import model.operator.Operator;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public class ExpressionFactory {

    public static Expression createExpression(Element first, Operator op,
            Element second) {
        return new BaseExpression(first, op, second);
    }
}
