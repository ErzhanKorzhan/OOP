package org.example;

import static org.example.PrimeChecker.isNonPrime;

public class Sequential   {
    public boolean hasNonPrime(int[] numbers) {
        for (int num : numbers) {
            if (isNonPrime(num)) return true;
        }
        return false;
    }
}