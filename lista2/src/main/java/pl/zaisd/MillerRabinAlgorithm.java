package pl.zaisd;

import java.util.Random;

public class MillerRabinAlgorithm implements PrimeNumbers {

    public static final long MAX_ITERATION = 100;

    @Override
    public boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        if (n == 3) return true;

        Random r = new Random();
        long powerIndex = 0;//s
        long powerMultiplier = n - 1;//d
        for (powerMultiplier = n-1; powerMultiplier % 2 == 0; powerIndex++) {
            powerMultiplier = powerMultiplier / 2;
        }
        for (long i = 0; i < MAX_ITERATION; i++) {
            long random = r.nextLong(2, n - 2);//a
            long x = quickPower(random, powerMultiplier, n);
            if (x != 1 && x != n-1) {
                for (int j = 0; j < powerIndex - 1; j++) {
                    x = (x * x) % n;
                    if (x == n - 1) {
                        break;
                    }
                }
                if (x != n - 1) {
                    return false;
                }
            }
        }
        return true;
    }
    public static long quickPower(long x, long y, long mod) {
        long result = 1;
        x = x % mod;
        while (y > 0) {
            if (y % 2 == 1) {
                result = (result * x) % mod;
            }
            y = y / 2;
            x = (x * x) % mod;
        }
        return result;
    }
}
