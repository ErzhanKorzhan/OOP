package org.example;

import java.util.concurrent.ExecutionException;

public abstract class ArrayPrimeChecker {
    public abstract boolean hasNonPrime(int[] numbers) throws InterruptedException, ExecutionException;
}
