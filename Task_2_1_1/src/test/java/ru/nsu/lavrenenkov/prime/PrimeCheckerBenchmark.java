package ru.nsu.lavrenenkov.prime;

import org.openjdk.jmh.annotations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static ru.nsu.lavrenenkov.prime.PrimeChecker.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 20, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class PrimeCheckerBenchmark {
    private static int[] nums;

    @Setup(Level.Trial)
    public static void setup() throws IOException {
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
    @Benchmark
    public void testMethod() {
        boolean result1 = containsNonPrimeSequential(nums);
    }

    @Benchmark
    public void thread2() throws InterruptedException {
        boolean result1 = containsNonPrimeThread(nums, 2);
    }

    @Benchmark
    public void thread3() throws InterruptedException {
        boolean result1 = containsNonPrimeThread(nums, 3);
    }
    @Benchmark
    public void thread4() throws InterruptedException {
        boolean result1 = containsNonPrimeThread(nums, 4);
    }
    @Benchmark
    public void thread5() throws InterruptedException {
        boolean result1 = containsNonPrimeThread(nums, 5);
    }
    @Benchmark
    public void thread6() throws InterruptedException {
        boolean result1 = containsNonPrimeThread(nums, 6);
    }
    @Benchmark
    public void thread7() throws InterruptedException {
        boolean result1 = containsNonPrimeThread(nums, 7);
    }
    @Benchmark
    public void thread8() throws InterruptedException {
        boolean result1 = containsNonPrimeThread(nums, 8);
    }
    @Benchmark
    public void parallelStream() {
        boolean result1 = hasNonPrimeParallelStream(nums);
    }
}
