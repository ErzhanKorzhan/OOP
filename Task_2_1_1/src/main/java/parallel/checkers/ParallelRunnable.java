package parallel.checkers;

import org.example.ArrayPrimeChecker;
import org.example.PrimeChecker;

public class ParallelRunnable extends ArrayPrimeChecker  {
    @Override
    public boolean hasNonPrime(int[] numbers) throws InterruptedException {
        final boolean[] hasNonPrime = {false};

        Runnable task = () -> {
            for (int number : numbers) {
                if (PrimeChecker.isNonPrime(number)) {
                    hasNonPrime[0] = true;
                    return;
                }
            }
        };
        task.run();

        return hasNonPrime[0];
    }
}