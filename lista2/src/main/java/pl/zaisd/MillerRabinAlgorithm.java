package pl.zaisd;

import java.util.Random;

public class MillerRabinAlgorithm implements PrimeNumbers {

    @Override
    public boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        Random r = new Random();
        long powerIndex = 0;//s
        long powerMultiplier = n - 1;//d
        while (powerMultiplier % 2 == 0) {
            powerIndex++;
            powerMultiplier = powerMultiplier / 2;
        }
        long maxIteration = 100;
        for (long i = 0; i < maxIteration; i++) {
            long random = r.nextLong(2, n - 2);//a
            if (Math.pow(random, powerMultiplier) % n != 1) {
                for (int j = 0; j < powerIndex; j++) {
                    if (Math.pow(random, powerMultiplier * Math.pow(2, j)) % n == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
