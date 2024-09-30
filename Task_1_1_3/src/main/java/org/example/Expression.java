package org.example;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.valueOf;

public abstract class Expression {
    public abstract void print();
    public abstract Expression derivative(String variable);

    public int eval(String s){
        Map<String, Integer> vars = new HashMap<>();
        String[] str = s.split("; ");
        for (String string : str) {
            String[] mops = string.split(" = ");
            vars.put(mops[0], valueOf(mops[1]));
        }
        return eval_map(vars);
    }
    public abstract int eval_map(Map<String, Integer> vars);
}

class Number extends Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0); // Производная константы
    }

    @Override
    public int eval_map(Map<String, Integer> vars) {
        return value;
    }
}

class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.print(name);
    }

    @Override
    public Expression derivative(String var) {
        if (name.equals(var))
        {
            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    public int eval_map(Map<String, Integer> vars) {
        return vars.get(name); // Получаем значение переменной
    }
}

class Add extends Expression {
    private final Expression left;
    private final Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("+");
        right.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }

    @Override
    public int eval_map(Map<String, Integer> vars) {
        return left.eval_map(vars) + right.eval_map(vars);
    }
}

class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
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
    public int eval_map(Map<String, Integer> vars) {
        return left.eval_map(vars) - right.eval_map(vars);
    }
}

class Mul extends Expression {
    private final Expression left;
    private final Expression right;

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
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
    public int eval_map(Map<String, Integer> vars) {
        return left.eval_map(vars) * right.eval_map(vars);
    }
}

class Div extends Expression {
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
    public Expression derivative(String var) {
        return new Div(new Sub(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var))), new Mul(right, right));
    }

    @Override
    public int eval_map(Map<String, Integer> vars) {
        return left.eval_map(vars) / right.eval_map(vars);
    }
}