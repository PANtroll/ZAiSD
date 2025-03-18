package pl.zaisd;

public class Main {

    public static void main(String[] args) {
        Fibonacci tablica = new Tablica();
        Fibonacci rekurencja = new Rekurencja();
        Fibonacci macierz = new Macierz();
        int number = 60;
//        int number = 10_000;
        for (int i = 10; i <= number; i+=10) {
        runWithStopwatch(rekurencja, i);
        runWithStopwatch(tablica, i);
        runWithStopwatch(macierz, i);
    }
    }

    private static void runWithStopwatch(Fibonacci rekurencja, int number) {
//        long startTime = System.currentTimeMillis();
        long startTime = System.nanoTime();
        long calculatedFibonacci1 = rekurencja.calculateFibonacci(number);
        long calculatedFibonacci2 = rekurencja.calculateFibonacci(number);
        long calculatedFibonacci3 = rekurencja.calculateFibonacci(number);
        if(calculatedFibonacci1 != calculatedFibonacci2 || calculatedFibonacci2 != calculatedFibonacci3) {
            throw new RuntimeException("Fibonacci numbers do not match");
        }
//        long duration = System.currentTimeMillis() - startTime;
        long duration = (System.nanoTime() - startTime)/3;
        System.out.println("Fibonacci for: " + number + " is: " + calculatedFibonacci3 + " in " + duration + "ms");
    }
}