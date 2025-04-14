package org.expr;

import org.example.Expression;

public class Number extends Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }

    @Override
    public int eval(String s) {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return value == ((Number) obj).value;
    }
}
