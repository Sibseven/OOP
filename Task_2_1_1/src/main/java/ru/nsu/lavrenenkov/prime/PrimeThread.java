package ru.nsu.lavrenenkov.prime;

import lombok.Getter;

public class PrimeThread extends Thread {

    private int[] numbers;
    @Getter
    private boolean result;
    public PrimeThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            if(!PrimeChecker.isPrime(number)) {
                result = true;
            }
        }
        result = false;
    }

}
