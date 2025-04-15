package pl.zaisd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static final String DELIMITER = ",";

    public static void main(String[] args) throws IOException {
        PrimeNumbers iteration = new Iteration();
        PrimeNumbers millerRabin = new MillerRabinAlgorithm();
        File path = new File("dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

        long i = (long) Math.pow(10, 8);
        long max = 9L * (long) Math.pow(10, 18);
        long increment = 2;
        for (; i <= max; i += increment) {
            Tuple result1 = runWithStopwatch(iteration, i);
            Tuple result2 = runWithStopwatch(millerRabin, i);
            if (result1.isPrime || result2.isPrime) {
                writer.write(i + DELIMITER + result1.isPrime + DELIMITER + result1.duration +
                        DELIMITER + result2.isPrime + DELIMITER + result2.duration + DELIMITER + new BigInteger("" + i).isProbablePrime(10) + "\r\n");
            }
            System.out.println();

            increment += Math.max(2L, (long) (0.01 * increment));
            if (increment % 2 == 0) {
                increment++;
            }
        }
        writer.close();
    }

    private static Tuple runWithStopwatch(PrimeNumbers method, long number) throws IOException {
        long startTime = System.nanoTime();
        boolean result = false;
        for (int i = 0; i < 5; i++) {
            result = method.isPrime(number);
        }
        long duration = (System.nanoTime() - startTime) / 5_000;// 10 invokes converted to micro
        System.out.println("Number: " + number + " isPrime: " + result + " in " + duration + "μs");
        return new Tuple(duration, result);
    }

    private static class Tuple {
        private final long duration;
        private final boolean isPrime;

        public Tuple(long duration, boolean isPrime) {
            this.duration = duration;
            this.isPrime = isPrime;
        }

        public long getDuration() {
            return duration;
        }

        public boolean isPrime() {
            return isPrime;
        }
    }
}