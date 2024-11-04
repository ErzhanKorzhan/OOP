package org.example;

public abstract class Expression {
    public abstract void print();
    public abstract Expression derivative(String variable);
    public abstract int eval(String s);
    public abstract Expression simplify();
}