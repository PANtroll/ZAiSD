package org.example;


public class Macierz implements Fibonacci {

    private long[][] fibMatrix, resultMatrix;

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
        resultMatrix = new long[2][2];
        resultMatrix = quickPower(fibMatrix, n-1);
        return resultMatrix[0][0];
    }

    private long[][] quickPower(long[][] matrix, int n) {
        if (n == 1) return matrix;
        long[][] y = quickPower(matrix, n / 2);
        y = multiplyMatrix(y, y);
        if (n % 2 == 1) y = multiplyMatrix(y, matrix);
        return y;
    }

    private long[][] multiplyMatrix(long[][] lastMatrix, long[][] fibMatrix) {
        long[][] tempMatrix = new long[2][2];
        tempMatrix[0][0] = multiplyScalars(lastMatrix[0][0], fibMatrix[0][0]) + multiplyScalars(lastMatrix[0][1], fibMatrix[1][0]);
        tempMatrix[0][1] = multiplyScalars(lastMatrix[0][0], fibMatrix[0][1]) + multiplyScalars(lastMatrix[0][1], fibMatrix[1][1]);
        tempMatrix[1][0] = multiplyScalars(lastMatrix[1][0], fibMatrix[0][0]) + multiplyScalars(lastMatrix[1][1], fibMatrix[1][0]);
        tempMatrix[1][1] = multiplyScalars(lastMatrix[1][0], fibMatrix[0][1]) + multiplyScalars(lastMatrix[1][1], fibMatrix[1][1]);
        return tempMatrix;
    }

    private long multiplyScalars(long x, long y) {
        return Math.floorMod(x * y, MOD);
    }
}
