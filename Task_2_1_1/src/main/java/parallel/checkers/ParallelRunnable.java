package parallel.checkers;

import org.example.ArrayPrimeChecker;
import org.example.PrimeChecker;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelRunnable extends ArrayPrimeChecker  {
    private final int threadCount;

    public ParallelRunnable(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public boolean hasNonPrime(int[] numbers) throws InterruptedException {
        AtomicBoolean hasNonPrime = new AtomicBoolean(false);
        List<Thread> threads = new ArrayList<>();

        // Разделение массива на части для каждого потока
        int chunkSize = (numbers.length + threadCount - 1) / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, numbers.length);
            Runnable task = () -> {
                for (int j = start; j < end; j++) {
                    // Если уже нашли не простое число, прерываем проверку
                    if (hasNonPrime.get()) {
                        return;
                    }
                    if (PrimeChecker.isNonPrime(numbers[j])) {
                        hasNonPrime.set(true);
                        return;
                    }
                }
            };
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start(); // Запуск потока (вызов start(), а не run())
        }

        // Ожидание завершения всех потоков
        for (Thread thread : threads) {
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
}