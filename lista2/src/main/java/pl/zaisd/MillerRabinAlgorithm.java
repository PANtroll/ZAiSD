package pl.zaisd;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabinAlgorithm implements PrimeNumbers {

    public static final long MAX_ITERATION = 10;

    @Override
    public boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        Random r = new Random();
        long powerIndex = 0;//s
        long powerMultiplier = n - 1;//d
        for (powerMultiplier = n-1; powerMultiplier % 2 == 0; powerIndex++) {
            powerMultiplier = powerMultiplier / 2;
        }
        BigInteger d = new BigInteger("" + powerMultiplier);
        BigInteger mod = new BigInteger("" + n);
        for (long i = 0; i < MAX_ITERATION; i++) {
            long random = r.nextLong(2, n - 2);//a
            BigInteger x = quickPower(new BigInteger(""+random), d, mod);
            if (x.compareTo(BigInteger.ONE) != 0 && x.compareTo(mod.subtract(BigInteger.ONE)) != 0) {
                for (long j = 0; j < powerIndex - 1; j++) {
                    x = (x.multiply(x)).mod(mod);
                    if (x.compareTo(mod.subtract(BigInteger.ONE)) == 0) {
                        break;
                    }
                }
                if (x.compareTo(mod.subtract(BigInteger.ONE)) != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public static BigInteger quickPower(BigInteger x, BigInteger y, BigInteger mod) {
        BigInteger result = BigInteger.ONE;
        x = x.mod(mod);
        while (y.compareTo(BigInteger.ZERO) > 0) {
            if (y.mod(BigInteger.TWO).compareTo(BigInteger.ONE) == 0) {
                result = (result.multiply(x)).mod(mod);
            }
            y = y.divide(BigInteger.TWO);
            x = (x.multiply(x)).mod(mod);
        }
        return result;
    }
}
