package parallel.checkers;

import org.example.ArrayPrimeChecker;
import org.example.PrimeChecker;

import java.util.Arrays;

public class ParallelStream extends ArrayPrimeChecker {
    @Override
    public boolean hasNonPrime(int[] numbers) {
        return Arrays.stream(numbers).parallel().anyMatch(PrimeChecker::isNonPrime);
    }
}
