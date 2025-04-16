package third.multiThreaded.evenOdd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class EvenOddUsingExecutorService {
    private static final int MAX = 10;
    private int num = 1;

    private final Semaphore oddSemaphore = new Semaphore(1);
    private final Semaphore evenSemaphore = new Semaphore(0);

    public static void main(String[] args) {
        EvenOddUsingExecutorService printer = new EvenOddUsingExecutorService();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(printer::printOdd);
        executorService.execute(printer::printEven);

        executorService.shutdown();
    }

    public void printOdd() {
        while (num <= MAX) {
            try {
                oddSemaphore.acquire();
                if (num % 2 != 0) {
                    System.out.println("Odd: " + num + " by " + Thread.currentThread().getName());
                    num++;
                }
                evenSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printEven() {
        while (num <= MAX) {
            try {
                evenSemaphore.acquire();
                if (num % 2 == 0) {
                    System.out.println("Even: " + num + " by " + Thread.currentThread().getName());
                    num++;
                }
                oddSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
