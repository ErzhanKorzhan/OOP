package org.example;

import java.util.concurrent.*;

import static org.example.PrimeChecker.isNonPrime;

public class ParallelThread {
    private final int threadCount;

    public ParallelThread(int threadCount) {
        this.threadCount = threadCount;
    }

    public boolean hasNonPrime(int[] numbers) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CompletionService<Boolean> completionService = new ExecutorCompletionService<>(executor);
        int chunkSize = (numbers.length / threadCount) + 1;

        for (int i = 0; i < threadCount; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, numbers.length);
            completionService.submit(() -> {
                for (int j = start; j < end; j++) {
                    if (isNonPrime(numbers[j])) return true;
                }
                return false;
            });
        }

        executor.shutdown();
        for (int i = 0; i < threadCount; i++) {
            if (completionService.take().get()) { // Ждем завершения задачи
                executor.shutdownNow();
                return true;
            }
        }
        return false;
    }
}