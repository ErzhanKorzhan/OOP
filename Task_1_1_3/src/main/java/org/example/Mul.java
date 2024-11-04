package org.example;

public class Mul extends Expression {
    private final Expression left;
    private final Expression right;

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression simplify() {
        Expression leftSim = left.simplify();
        Expression rightSim = right.simplify();

        if (leftSim instanceof Number && rightSim instanceof Number){
            return new Number(leftSim.eval(null) * rightSim.eval(null));
        }
        if ((leftSim instanceof Number && leftSim.eval(null) == 0) || (rightSim instanceof Number && rightSim.eval(null) == 0)) {
            return new Number(0);
        }
        if (leftSim instanceof Number && leftSim.eval(null) == 1) {
            return rightSim;
        }
        if (rightSim instanceof Number && rightSim.eval(null) == 1) {
            return leftSim;
        }
        return new Mul(leftSim, rightSim);
    }

    @Override
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("*");
        right.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var)));
    }

    @Override
    public int eval(String s) {
        return left.eval(s) * right.eval(s);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return (((Mul) obj).left.equals(left) && ((Mul) obj).right.equals(right)) || (((Mul) obj).left.equals(right) && ((Mul) obj).right.equals(left));
    }
}
