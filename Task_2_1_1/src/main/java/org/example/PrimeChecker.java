package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class PrimeChecker {
    private static boolean isNonPrime(int num) {
        if (num <= 1) return true;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return true;
        }
        return false;
    }

    public static boolean hasNonPrimeSequential(int[] numbers) {
        for (int num : numbers) {
            if (isNonPrime(num)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNonPrimeParallelThreads(int[] numbers, int threadCount) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Boolean>> futures = new CopyOnWriteArrayList<>();
        int chunkSize = (int) Math.ceil((double) numbers.length / threadCount);

        for (int i = 0; i < numbers.length; i += chunkSize) {
            int start = i;
            int end = Math.min(i + chunkSize, numbers.length);
            futures.add(executor.submit(() -> {
                for (int j = start; j < end; j++) {
                    if (isNonPrime(numbers[j])) {
                        return true;
                    }
                }
                return false;
            }));
        }

        for (Future<Boolean> future : futures) {
            if (future.get()) {
                executor.shutdown();
                return true;
            }
        }

        executor.shutdown();
        return false;
    }

    public static boolean hasNonPrimeParallelStream(int[] numbers) {
        return Arrays.stream(numbers).parallel().anyMatch(PrimeChecker::isNonPrime);
    }
}
