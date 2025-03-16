package org.example;

public class Main {

    public static void main(String[] args) {
        Fibonacci tablica = new Tablica();
        Fibonacci rekurencja = new Rekurencja();
        Fibonacci macierz = new Macierz();
//        int number = 30;
        int number = 10_000;
        for (int i = 20; i <= number; i+=10) {
//        int numberRecur = 20;
//        runWithStopwatch(rekurencja, numberRecur);
//        runWithStopwatch(rekurencja, i);
        runWithStopwatch(tablica, i);
        runWithStopwatch(macierz, i);
    }
    }

    private static void runWithStopwatch(Fibonacci rekurencja, int number) {
        long startTime = System.nanoTime();
        long calculatedFibonacci = rekurencja.calculateFibonacci(number);
        long duration = System.nanoTime() - startTime;
        System.out.println("Fibonacci for: " + number + " is: " + calculatedFibonacci + " in " + duration + "ms");
    }
}