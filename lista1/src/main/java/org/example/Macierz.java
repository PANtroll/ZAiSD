package org.example;


public class Macierz implements Fibonacci {

    private long[][] fibMatrix, lastMatrix, tempMatrix;

    @Override
    public long calculateFibonacci(int n) {
        if (n < 2) {
            return n;
        }
        fibMatrix = new long[2][2];
        fibMatrix[0][0] = 1;
        fibMatrix[0][1] = 1;
        fibMatrix[1][0] = 1;
        fibMatrix[1][1] = 0;
        lastMatrix = new long[2][2];
        tempMatrix = new long[2][2];
        clone(fibMatrix, lastMatrix);
        for (int i = 2; i < n; i++) {
            tempMatrix[0][0] = multiply(lastMatrix[0][0], fibMatrix[0][0]) + multiply(lastMatrix[0][1], fibMatrix[1][0]);
            tempMatrix[0][1] = multiply(lastMatrix[0][0], fibMatrix[0][1]) + multiply(lastMatrix[0][1], fibMatrix[1][1]);
            tempMatrix[1][0] = multiply(lastMatrix[1][0], fibMatrix[0][0]) + multiply(lastMatrix[1][1], fibMatrix[1][0]);
            tempMatrix[1][1] = multiply(lastMatrix[1][0], fibMatrix[0][1]) + multiply(lastMatrix[1][1], fibMatrix[1][1]);
            clone(tempMatrix, lastMatrix);
        }
        return lastMatrix[0][0];
    }

    private long multiply(long x, long y) {
        return Math.floorMod(x * y, MOD);
    }

    private void clone(long[][] source, long[][] target) {
        target[0][0] = source[0][0];
        target[0][1] = source[0][1];
        target[1][0] = source[1][0];
        target[1][1] = source[1][1];
    }
}
