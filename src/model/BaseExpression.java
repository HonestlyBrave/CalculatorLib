package model;

import model.operator.Operator;

/**
 * Leaf class of the math composite.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public final class BaseExpression implements Expression {

    // <editor-fold defaultstate="collapsed" desc="Private attributes. Click on + sign to show.">
    /**
     * Elements of a binomial expression.
     */
    private Element operand1, operand2;

    /**
     * Operator of a binomial expression.
     */
    private Operator operator;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors. Click on + sign to show.">
    /**
     * Default constructor.
     */
    public BaseExpression() {
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public methods. Click on + sign to show.">
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
        return "(" + this.operand1 + this.operator + this.operand2 + ")";
    }
    // </editor-fold>
}
