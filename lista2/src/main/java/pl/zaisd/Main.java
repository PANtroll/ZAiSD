package pl.zaisd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static final String DELIMITER = ",";

    public static void main(String[] args) throws IOException {
        PrimeNumbers iteration = new Iteration();
        PrimeNumbers millerRabin = new MillerRabinAlgorithm();

        File path = new File("dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

//        long max = 20;
        long min = 511L;
        long max = (long) Math.pow(10, 10) /3;//18
        Random random = new Random();
//        System.out.println(MillerRabinAlgorithm.quickPower(2, 2, 10));
//        System.out.println(MillerRabinAlgorithm.quickPower(3, 3, 10));
        for (; min <= max; min += random.nextInt(1_000, 1_000_000) * 2L) {
            Tuple result1 = runWithStopwatch(iteration, min);
            Tuple result2 = runWithStopwatch(millerRabin, min);
            if (result1.isPrime || result2.isPrime)
                writer.write(min + DELIMITER + result1.isPrime + DELIMITER + result1.duration +
                        DELIMITER + result2.isPrime + DELIMITER + result2.duration + "\r\n");
            System.out.println();
        }
        writer.close();
    }

    private static Tuple runWithStopwatch(PrimeNumbers method, long number) throws IOException {
        long startTime = System.nanoTime();
        boolean result = false;
        for (int i = 0; i < 10; i++) {
            result = method.isPrime(number);
        }
        long duration = (System.nanoTime() - startTime) / 10_000;// 10 invokes converted to micro
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