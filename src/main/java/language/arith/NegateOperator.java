package language.arith;

import language.Operand;

/**
 * The {@link NegateOperator} is an operator that performs negation
 * on a single integer.
 * @author jcollard, jddevaug
 */
public class NegateOperator extends UnaryOperator<Integer> {
    /**
     * {@inheritDoc}
     */
    @Override
    public Operand<Integer> performOperation() {
        Operand<Integer> op0 = this.getOp0();

        if(op0 == null) {
            throw new IllegalStateException("negate error");
        }

        Integer result = -op0.getValue();
        return new Operand<>(result);
    }
}
