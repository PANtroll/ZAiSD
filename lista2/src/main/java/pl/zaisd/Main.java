package pl.zaisd;

public class Main {

    public static void main(String[] args) {
        PrimeNumbers iteration = new Iteration();
        PrimeNumbers millerRabin = new MillerRabinAlgorithm();
        long max = 20;
//        long max = 100_000;
        for (long i = 4; i < max; i++) {
            runWithStopwatch(iteration, i);
            runWithStopwatch(millerRabin, i);
            System.out.println();
        }

    }

    private static void runWithStopwatch(PrimeNumbers method, long number) {
        long startTime = System.nanoTime();
        boolean result = false;
        for (int i = 0; i < 10; i++) {
            result = method.isPrime(number);
        }
        long duration = (System.nanoTime() - startTime) / 10_000;// 10 invokes converted to micro
        System.out.println("Number: " + number + " isPrime: " + result + " in " + duration + "μs");
    }
}