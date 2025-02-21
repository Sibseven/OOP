package ru.nsu.lavrenenkov.prime;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Class for testing PrimeChecker.
 */
public class PrimeCheckerTest {
    private static int[] nums;

    /**
     * Method to read primes from file.
     *
     * @throws Exception if no file found
     */
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        InputStream inputStream = PrimeCheckerTest.class.getClassLoader().getResourceAsStream("810BigPrimes");

        if (inputStream == null) {
            throw new IOException("Файл не найден в ресурсах!");
        }
        PrimeChecker checker = new PrimeChecker();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<Integer> numbers = reader.lines()
                    .map(Integer::parseInt)
                    .filter(checker::isPrime)
                    .toList();
            nums = numbers.stream().mapToInt(i -> i).toArray();
        }
    }

    @Test
    public void testIsPrime() {
        PrimeChecker checker = new PrimeChecker();
        boolean isPrime = checker.isPrime(1);
        assertTrue(isPrime);
    }

    @Test
    public void testIsNotPrime() {
        PrimeChecker checker = new PrimeChecker();
        boolean isPrime = checker.isPrime(4);
        assertFalse(isPrime);
    }

    @Test
    public void testSequential() {
        double resMs = 0;
        PrimeChecker checker = new PrimeChecker();
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = checker.containsNonPrimeSequential(nums);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start) / 1e6;
        }
        resMs /= 100;
        System.out.println("Sequential" + resMs
                            + "is Prime calls " + checker.getCounter() / 100);
    }

    @Test
    public void testThread2() throws InterruptedException {
        double resMs = 0;
        PrimeChecker checker = new PrimeChecker();
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = checker.containsNonPrimeThread(nums, 2);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start) / 1e6;
        }
        resMs /= 100;
        System.out.println("Two Threads" + resMs
                            + "is Prime calls " + checker.getCountThreads() / 100);
    }

    @Test
    public void testThread3() throws InterruptedException {
        double resMs = 0;
        PrimeChecker checker = new PrimeChecker();
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = checker.containsNonPrimeThread(nums, 3);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start) / 1e6;
        }
        resMs /= 100;
        System.out.println("Three Threads" + resMs
                            + "is Prime calls " + checker.getCountThreads() / 100);
    }

    @Test
    public void testThread4() throws InterruptedException {
        double resMs = 0;
        PrimeChecker checker = new PrimeChecker();
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = checker.containsNonPrimeThread(nums, 4);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start) / 1e6;
        }
        resMs /= 100;
        System.out.println("Four Threads" + resMs
                            + "is Prime calls " + checker.getCountThreads() / 100);
    }

    @Test
    public void testThread8() throws InterruptedException {
        double resMs = 0;
        PrimeChecker checker = new PrimeChecker();
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = checker.containsNonPrimeThread(nums, 8);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start) / 1e6;
        }
        resMs /= 100;
        System.out.println("Eight Threads" + resMs
                            + "is Prime calls " + checker.getCountThreads() / 100);
    }

    @Test
    public void testParallelStream() {
        double resMs = 0;
        PrimeChecker checker = new PrimeChecker();
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = checker.hasNonPrimeParallelStream(nums);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start) / 1e6;
        }
        resMs /= 100;
        System.out.println("Parallel stream" + resMs
                            + "is Prime calls " + checker.getCounter() / 100);
    }

}
