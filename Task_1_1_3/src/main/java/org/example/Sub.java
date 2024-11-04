package org.example;

public class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression simplify() {
        Expression leftSim = left.simplify();
        Expression rightSim = right.simplify();

        if (leftSim instanceof Number && rightSim instanceof Number) {
            return new Number(leftSim.eval(null) - rightSim.eval(null));
        }
        if (rightSim instanceof Number && rightSim.eval(null) == 0) {
            return leftSim;
        }
        if (leftSim.equals(rightSim)) {
            return new Number(0);
        }
        return new Sub(leftSim, rightSim);
    }

    @Override
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("-");
        right.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }

    @Override
    public int eval(String s) {
        return left.eval(s) - right.eval(s);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return ((Sub) obj).left.equals(left) && ((Sub) obj).right.equals(right);
    }
}
