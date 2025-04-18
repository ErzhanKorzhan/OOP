package parallel.checkers;

import org.example.ArrayPrimeChecker;
import org.example.PrimeChecker;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelThread extends ArrayPrimeChecker {
    private final int threadCount;

    public ParallelThread(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public boolean hasNonPrime(int[] numbers) throws InterruptedException {
        AtomicBoolean hasNonPrime = new AtomicBoolean(false);
        List<WorkerThread> threads = new ArrayList<>();

        // Разделение массива на части для каждого потока
        int chunkSize = (numbers.length + threadCount - 1) / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, numbers.length);
            WorkerThread thread = new WorkerThread(numbers, start, end, hasNonPrime);
            threads.add(thread);
            thread.start();
        }

        // Ожидание завершения всех потоков
        for (WorkerThread thread : threads) {
            while (thread.isAlive()) {
                thread.join(100); // Ожидание с таймаутом для проверки флага
                if (hasNonPrime.get()) {
                    // Прерываем все потоки для досрочного завершения
                    for (Thread t : threads) {
                        t.interrupt();
                    }
                    return true;
                }
            }
        }

        return hasNonPrime.get();
    }

    private static class WorkerThread extends Thread {
        private final int[] numbers;
        private final int start;
        private final int end;
        private final AtomicBoolean hasNonPrime;

        public WorkerThread(int[] numbers, int start, int end, AtomicBoolean hasNonPrime) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
            this.hasNonPrime = hasNonPrime;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                // Если уже нашли не простое число, прерываем проверку
                if (hasNonPrime.get()) {
                    return;
                }
                if (PrimeChecker.isNonPrime(numbers[i])) {
                    hasNonPrime.set(true);
                    return;
                }
            }
        }
    }
}