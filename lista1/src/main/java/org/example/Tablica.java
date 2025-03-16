package org.example;

public class Tablica implements Fibonacci {

    private long[] fibTable;

    @Override
    public long calculateFibonacci(int n) {
        if (n < 2) {
            return n;
        }
        fibTable = new long[n + 1];
        fibTable[0] = 0;
        fibTable[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            fibTable[i] = Math.floorMod(fibTable[i - 1] + fibTable[i - 2], MOD);
        }
        return fibTable[n];
    }
}