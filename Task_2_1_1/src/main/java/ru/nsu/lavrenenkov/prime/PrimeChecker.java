package ru.nsu.lavrenenkov.prime;

import lombok.Getter;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class for checking if array contains non-Prime numbers.
 * 3 realisations: Sequential, Thread, ParallelStream
 */
public class PrimeChecker {
    private AtomicInteger counter = new AtomicInteger(0);
    @Getter
    private int countThreads = 0;

    /**
     * Method to determine if at least one num in array is not prime
     *
     * @param numbers - array to be checked
     *
     * @return - true if n>=1 non primes, false
     */
    public boolean containsNonPrimeSequential(int[] numbers) {
        for (int num : numbers) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method for checking if number is Prime.
     *
     * @param num - number to be checked.
     *
     * @return true if number prime, false otherwise
     */
    boolean isPrime(int num) {
        counter.incrementAndGet();

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param numbers - to be checked
     *
     * @param numThreads - number of threads
     *
     * @return - true if at least one number isn`t prime
     *
     * @throws InterruptedException - if number is interrupted
     */
    public boolean containsNonPrimeThread(int[] numbers, int numThreads) throws InterruptedException {
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
            countThreads += thread.getCounter();
            if (thread.isResult()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check Primes with ParallelStreams.
     *
     * @param numbers - to be checked
     *
     * @return - true if at least one number isn`t prime
     */
    public boolean hasNonPrimeParallelStream(int[] numbers) {
        return Arrays.stream(numbers).parallel().anyMatch(num -> !isPrime(num));
    }

    /**
     * Method for getting counter of isPrimecalls
     * @return - counter value
     */
    public int getCounter() {
        return counter.intValue();
    }
}
