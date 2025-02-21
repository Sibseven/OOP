package ru.nsu.lavrenenkov.prime;

import lombok.Getter;

/**
 * Thread for prime checking.
 *
 */
public class PrimeThread extends Thread {

    private int[] numbers;
    private PrimeChecker checker = new PrimeChecker();
    @Getter
    private boolean result;

    public PrimeThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            if (!checker.isPrime(number)) {
                result = true;
            }
        }
        result = false;
    }

    /**
     * Method for counting calls.
     *
     * @return number of isPrime calls.
     */
    public int getCounter() {
        return checker.getCounter();
    }

}
