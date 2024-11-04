package org.example;

public class Div extends Expression {
    private final Expression left;
    private final Expression right;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("/");
        right.print();
        System.out.print(")");
    }

    @Override
    public Expression simplify() {
        Expression leftSim = left.simplify();
        Expression rightSim = right.simplify();


        if (leftSim instanceof Number && rightSim instanceof Number) {
            return new Number(leftSim.eval(null) / rightSim.eval(null));
        }
        if (leftSim instanceof Number && leftSim.eval(null) == 0){
            return leftSim;
        }
        if (rightSim instanceof Number && rightSim.eval(null) == 1){
            return leftSim;
        }
        return new Div(leftSim, rightSim);
    }

    @Override
    public Expression derivative(String var) {
        return new Div(new Sub(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var))), new Mul(right, right));
    }

    @Override
    public int eval(String s) {
        return left.eval(s) / right.eval(s);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return ((Div) obj).left.equals(left) && ((Div) obj).right.equals(right);
    }
}
