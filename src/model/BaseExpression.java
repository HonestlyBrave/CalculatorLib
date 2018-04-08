package model;

import model.operator.Operator;
import model.tostring.DisplayStrategy;
import model.tostring.InFix;

/**
 * Composite class of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public final class BaseExpression implements Expression {

    /**
     * Elements of a binomial expression.
     */
    private Element operand1, operand2;

    /**
     * Operator of a binomial expression.
     */
    private Operator operator;

    /**
     * Default strategy infix.
     */
    private DisplayStrategy strategy = new InFix();

    /**
     * Default constructor.
     */
    public BaseExpression() {
    }

    /**
     * Constructor requiring 2 elements.
     *
     * @param operand1 first element of binomial Base Expression
     * @param operand2 second element of binomial Base Expression
     */
    public BaseExpression(Element operand1, Element operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    /**
     * Constructor requiring 2 elements and an operator.
     *
     * @param operand1 first element of binomial Base Expression
     * @param operator second element of binomial Base Expression
     * @param operand2 third element of binomial Base Expression
     */
    public BaseExpression(Element operand1, Operator operator, Element operand2) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
    }

    /**
     * Constructor requiring 2 elements and a display strategy.
     *
     * @param operand1 first element of binomial Base Expression
     * @param operand2 second element of binomial Base Expression
     * @param strategy style of display element of binomial Base Expression
     */
    public BaseExpression(Element operand1, Element operand2,
            DisplayStrategy strategy) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.strategy = strategy;
    }

    /**
     * Constructor requiring 2 elements, an operator and a display strategy.
     *
     * @param operand1 first element of binomial Base Expression
     * @param operator second element of binomial Base Expression
     * @param operand2 third element of binomial Base Expression
     * @param strategy style of display output
     */
    public BaseExpression(Element operand1, Operator operator, Element operand2,
            DisplayStrategy strategy) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
        this.strategy = strategy;
    }

    /**
     * Add first element.
     *
     * @param element of binomial Base Expression
     */
    public void addFirst(Element element) {
        this.operand1 = element;
    }

    /**
     * Add second element.
     *
     * @param element of binomial Base Expression
     */
    public void addSecond(Element element) {
        this.operand2 = element;
    }

    @Override
    public double evaluate() {
        return operator.calculate(this.operand1, this.operand2);
    }

    /**
     *
     * @return text representation of base expression
     */
    @Override
    public String toString() {
        return strategy.display(operator, this.operand1, this.operand2);
    }
}
