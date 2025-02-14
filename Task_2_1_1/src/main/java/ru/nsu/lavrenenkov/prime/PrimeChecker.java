package ru.nsu.lavrenenkov.prime;
import java.util.Arrays;

public class PrimeChecker {
    public static boolean containsNonPrimeSequential(int[] numbers) {
        for (int num : numbers) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }

    static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsNonPrimeThread(int[] numbers, int numThreads) throws InterruptedException {
        final int length = numbers.length;
        final int chunkSize = (int) Math.ceil(length / (double) numThreads);
        PrimeThread[] threads = new PrimeThread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, length);
            threads[i] = new PrimeThread(Arrays.copyOfRange(numbers, start, end));
            threads[i].start();
        }

        for (PrimeThread thread : threads) {
            thread.join();
            if (thread.isResult())
                return true;
        }
        return false;
    }

    public static boolean hasNonPrimeParallelStream(int[] numbers) {
        return Arrays.stream(numbers).parallel().anyMatch(num -> !isPrime(num));
    }

}
