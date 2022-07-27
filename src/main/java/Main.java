import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // create an executor
        ExecutorService executor = Executors.newFixedThreadPool(4);

        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            // submit tasks to your executor
            executor.submit(new PrimeLogger(num));
        }
        executor.shutdown();
    }
}

class PrimeLogger implements Runnable {
    private final int num;

    public PrimeLogger(int num) {
        this.num = num;
    }

    boolean isPrime(int possiblePrime) {
        if (possiblePrime == 0 || possiblePrime == 1)
            return false;
        return !IntStream.rangeClosed(2, possiblePrime / 2).anyMatch(number -> possiblePrime % number == 0);
    }

    @Override
    public void run() {
        // print num if it is prime
        if (isPrime(num))
            System.out.println(num + " is a prime number");
    }
}