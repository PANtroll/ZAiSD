package org.example;

public class Rekurencja implements Fibonacci {

    @Override
    public long calculateFibonacci(int n) {
        if (n < 2) {
            return n;
        }
        return (calculateFibonacci(n - 1) + calculateFibonacci(n - 2));
    }
}
