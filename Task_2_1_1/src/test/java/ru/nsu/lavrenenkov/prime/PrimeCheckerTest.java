package ru.nsu.lavrenenkov.prime;

import static org.junit.jupiter.api.Assertions.*;
import static ru.nsu.lavrenenkov.prime.PrimeChecker.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;


public class PrimeCheckerTest {
    private static int[] nums;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        InputStream inputStream = PrimeCheckerTest.class.getClassLoader().getResourceAsStream("810BigPrimes");

        if (inputStream == null) {
            throw new IOException("Файл не найден в ресурсах!");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<Integer> numbers = reader.lines()
                    .map(Integer::parseInt)
                    .filter(PrimeChecker::isPrime)
                    .collect(Collectors.toList());
            nums = numbers.stream().mapToInt(i -> i).toArray();
        }
    }
    @Test
    public void testIsPrime() {
        boolean isPrime = PrimeChecker.isPrime(1);
        assertTrue(isPrime);
    }
    @Test
    public void testIsNotPrime() {
        boolean isPrime = PrimeChecker.isPrime(4);
        assertFalse(isPrime);
    }

    @Test
    public void testSequential() {
        double resMs = 0;
        for(int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = containsNonPrimeSequential(nums);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start)/ 1e6 ;
        }
        resMs /= 100;
        System.out.println("Sequential" + resMs);
    }

    @Test
    public void testThread2() throws InterruptedException {
        double resMs = 0;
        for(int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = containsNonPrimeThread(nums, 2);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start)/ 1e6 ;
        }
        resMs /= 100;
        System.out.println("Two Threads" + resMs);
    }
    @Test
    public void testThread3() throws InterruptedException {
        double resMs = 0;
        for(int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = containsNonPrimeThread(nums, 3);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start)/ 1e6 ;
        }
        resMs /= 100;
        System.out.println("Three Threads" + resMs);
    }
    @Test
    public void testThread4() throws InterruptedException {
        double resMs = 0;
        for(int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = containsNonPrimeThread(nums, 4);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start)/ 1e6 ;
        }
        resMs /= 100;
        System.out.println("Four Threads" + resMs);
    }
    @Test
    public void testThread8() throws InterruptedException {
        double resMs = 0;
        for(int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = containsNonPrimeThread(nums, 8);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start)/ 1e6 ;
        }
        resMs /= 100;
        System.out.println("Eight Threads" + resMs);
    }

    @Test
    public void testParallelStream() throws InterruptedException {
        double resMs = 0;
        for(int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            boolean result1 = hasNonPrimeParallelStream(nums);
            assertFalse(result1);
            long end = System.nanoTime();
            resMs += (end - start)/ 1e6 ;
        }
        resMs /= 100;
        System.out.println("Parallel stream"+ resMs);
    }

}
