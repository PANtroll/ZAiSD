package pl.zaisd;

public class Main {

    public static void main(String[] args) {
    }

    private static void runWithStopwatch(Object method, int number) {
        long startTime = System.nanoTime();
        long calculatedFibonacci = 0;
        for (int i = 0; i < 10; i++) {
            calculatedFibonacci = method.calculateFibonacci(number);
        }
        long duration = (System.nanoTime() - startTime) / 10_000;// 10 invokes converted to micro
        System.out.println("Fibonacci for: " + number + " is: " + calculatedFibonacci + " in " + duration + "μs");
    }
}