package pl.zaisd;

public class Main {

    public static void main(String[] args) {
        Fibonacci rekurencja = new Rekurencja();
        Fibonacci tablica = new Tablica();
        Fibonacci macierz = new Macierz();
        int stopValue = 40;
//        int stopValue = 10_000_000;
        int increment = 1;
        for (int i = 1; i <= stopValue; i += increment) {
            runWithStopwatch(rekurencja, i);//uncomment if you want to use first algorithm
            runWithStopwatch(tablica, i);
            runWithStopwatch(macierz, i);
//            if(i%(increment*10)==0) {//makes increment 10 times bigger so increment is always about to 10%
//                increment *= 10;
//            }
//            increment *= 2; //used in last algorithm
        }
    }

    private static void runWithStopwatch(Fibonacci method, int number) {
        long startTime = System.nanoTime();
        long calculatedFibonacci = 0;
        for (int i = 0; i < 10; i++) {
            calculatedFibonacci = method.calculateFibonacci(number);
        }
        long duration = (System.nanoTime() - startTime) / 10_000;// 10 invokes converted to micro
        System.out.println("Fibonacci for: " + number + " is: " + calculatedFibonacci + " in " + duration + "μs");
    }
}