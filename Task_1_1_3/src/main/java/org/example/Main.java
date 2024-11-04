package org.example;

public class Main {
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Variable("x"), new Number(2)));
        e.print();
        System.out.println();
        int result = e.eval("x = 10; y = 13");
        System.out.println(result);
    }
}