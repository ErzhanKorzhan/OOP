package org.example;

public class PrimeChecker {
    public static boolean isNonPrime(int num) {
        if (num <= 1) return true;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return true;
        }
        return false;
    }
}