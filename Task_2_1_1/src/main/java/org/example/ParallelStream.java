package org.example;

import java.util.Arrays;

public class ParallelStream {
    public boolean hasNonPrime(int[] numbers) {
        return Arrays.stream(numbers).parallel().anyMatch(PrimeChecker::isNonPrime);
    }
}
