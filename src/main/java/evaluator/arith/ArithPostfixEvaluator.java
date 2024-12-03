package evaluator.arith;

import evaluator.Evaluator;
import evaluator.IllegalPostfixExpressionException;
import language.BinaryOperator;
import language.Operand;
import language.Operator;
import language.arith.UnaryOperator;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import stack.StackUnderflowException;

/**
 * An {@link ArithPostfixEvaluator} is a post fix evaluator
 * over simple arithmetic expressions.
 */
public class ArithPostfixEvaluator implements Evaluator<Integer> {
    private final StackInterface<Operand<Integer>> stack;

    public ArithPostfixEvaluator() {
        stack = new LinkedStack<>();
    }

    @Override
    public Integer evaluate(String expr) {
        ArithPostfixParser parser = new ArithPostfixParser(expr);

        while (parser.hasNext()) {
            switch (parser.nextType()) {
                case OPERAND:
                    Operand<Integer> operand = parser.nextOperand();
                    stack.push(operand);
                    break;

                case OPERATOR:
                    Operator<Integer> operator = parser.nextOperator();
                    try {
                        if (operator instanceof UnaryOperator) {
                            Operand<Integer> op = stack.pop();
                            operator.setOperand(0, op);
                        } else if (operator instanceof BinaryOperator) {
                            Operand<Integer> op2 = stack.pop();
                            Operand<Integer> op1 = stack.pop();
                            operator.setOperand(1, op2);
                            operator.setOperand(0, op1);
                        }
                        stack.push(operator.performOperation());
                    } catch (StackUnderflowException e) {
                        throw new IllegalPostfixExpressionException("Not enough operands");
                    }
                    break;

                default:
                    throw new IllegalStateException("Invalid parser state");
            }
        }

        try {
            if (stack.size() != 1) {
                throw new IllegalPostfixExpressionException("Too many operands");
            }
            return stack.pop().getValue();
        } catch (StackUnderflowException e) {
            throw new IllegalPostfixExpressionException("Empty expression");
        }
    }
}