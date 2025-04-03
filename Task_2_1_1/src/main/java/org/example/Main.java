package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] testArray1 = {6, 8, 7, 13, 5, 9, 4};
        int[] testArray2 = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};

        Sequential sequentialChecker = new Sequential();
        ParallelThread parallelThreadChecker = new ParallelThread(4);
        ParallelStream parallelStreamChecker = new ParallelStream();

        System.out.println("Sequential:" + sequentialChecker.hasNonPrime(testArray1));
        System.out.println("Parallel Threads:" + parallelThreadChecker.hasNonPrime(testArray1));
        System.out.println("Parallel Stream:" + parallelStreamChecker.hasNonPrime(testArray1));
        System.out.println("Sequential:" + sequentialChecker.hasNonPrime(testArray2));
        System.out.println("Parallel Threads:" + parallelThreadChecker.hasNonPrime(testArray2));
        System.out.println("Parallel Stream:" + parallelStreamChecker.hasNonPrime(testArray2));
    }
}