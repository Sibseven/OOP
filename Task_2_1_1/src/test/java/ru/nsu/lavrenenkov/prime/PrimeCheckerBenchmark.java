package ru.nsu.lavrenenkov.prime;

import org.openjdk.jmh.annotations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * Benchmark for PrimeChecker.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 20, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class PrimeCheckerBenchmark {
    private int[] nums;

    @Setup(Level.Trial)
    public void setup() throws IOException {
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
            this.nums = numbers.stream().mapToInt(i -> i).toArray();
        }
    }
    @Benchmark
    public void testMethod() {
        PrimeChecker checker = new PrimeChecker();
        boolean result1 = checker.containsNonPrimeSequential(nums);
        System.out.println("Sequential is prime calls " + checker.getCounter());
    }

    @Benchmark
    public void thread2() throws InterruptedException {
        PrimeChecker checker = new PrimeChecker();
        boolean result1 = checker.containsNonPrimeThread(nums, 2);
        System.out.println("2 Thread is prime calls " + checker.getCounter());
    }

    @Benchmark
    public void thread3() throws InterruptedException {
        PrimeChecker checker = new PrimeChecker();
        boolean result1 = checker.containsNonPrimeThread(nums, 3);
        System.out.println("3 Thread is prime calls " + checker.getCounter());
    }
    @Benchmark
    public void thread4() throws InterruptedException {
        PrimeChecker checker = new PrimeChecker();
        boolean result1 = checker.containsNonPrimeThread(nums, 4);
        System.out.println("4 Thread is prime calls " + checker.getCounter());
    }
    @Benchmark
    public void thread5() throws InterruptedException {
        PrimeChecker checker = new PrimeChecker();
        boolean result1 = checker.containsNonPrimeThread(nums, 5);
        System.out.println("5 Thread is prime calls " + checker.getCounter());
    }
    @Benchmark
    public void thread6() throws InterruptedException {
        PrimeChecker checker = new PrimeChecker();
        boolean result1 = checker.containsNonPrimeThread(nums, 6);
        System.out.println("6 Thread is prime calls " + checker.getCounter());
    }
    @Benchmark
    public void thread7() throws InterruptedException {
        PrimeChecker checker = new PrimeChecker();
        boolean result1 = checker.containsNonPrimeThread(nums, 7);
        System.out.println("7 Thread is prime calls " + checker.getCounter());
    }
    @Benchmark
    public void thread8() throws InterruptedException {
        PrimeChecker checker = new PrimeChecker();
        boolean result1 = checker.containsNonPrimeThread(nums, 8);
        System.out.println("8 Thread is prime calls " + checker.getCounter());
    }
    @Benchmark
    public void parallelStream() {
        PrimeChecker checker = new PrimeChecker();
        boolean result1 = checker.hasNonPrimeParallelStream(nums);
        System.out.println("Parallel stream is prime calls " + checker.getCounter());
    }
}
