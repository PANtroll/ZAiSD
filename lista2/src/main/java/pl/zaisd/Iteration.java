package pl.zaisd;

public class Iteration implements PrimeNumbers {
    @Override
    public boolean isPrime(long n) {
        long max = (long) Math.sqrt(n) + 1;
        for (long i = 2L; i <= max; i++) {
            if(n % i == 0L) {
                return false;
            }
        }
        return true;
    }
}
