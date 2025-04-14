package org.expr;

import org.example.Expression;

public class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.print(name);
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public Expression derivative(String var) {
        return new Number(name.equals(var) ? 1 : 0);
    }

    @Override
    public int eval(String s) {
        String[] str = s.split("; ");
        for (String string : str) {
            String[] mops = string.split(" = ");
            if (mops[0].equals(name)){
                return Integer.parseInt(mops[1]);
            }
        }
        throw new IllegalArgumentException("Unexpected variable");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return ((Variable) obj).name.equals(name);
    }
}
