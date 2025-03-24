package pl.zaisd;

public class Iteration implements PrimeNumbers {
    @Override
    public boolean isPrime(long n) {
        long max = (long) Math.sqrt(n);
        for (int i = 2; i <= max; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
