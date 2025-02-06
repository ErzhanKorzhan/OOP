package org.example;

import java.util.concurrent.ExecutionException;
import static org.example.PrimeChecker.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] testArray1 = {6, 8, 7, 13, 5, 9, 4};
        int[] testArray2 = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};

        System.out.println("Sequential: " + hasNonPrimeSequential(testArray1));
        System.out.println("Parallel Threads: " + hasNonPrimeParallelThreads(testArray1, 4));
        System.out.println("Parallel Stream: " + hasNonPrimeParallelStream(testArray1));

        System.out.println("Sequential: " + hasNonPrimeSequential(testArray2));
        System.out.println("Parallel Threads: " + hasNonPrimeParallelThreads(testArray2, 4));
        System.out.println("Parallel Stream: " + hasNonPrimeParallelStream(testArray2));
    }
}
