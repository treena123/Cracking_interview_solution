package third.multiThreaded.evenOdd;

import java.util.concurrent.Semaphore;

public class EvenOddUsingSemaphore {
    private static final int MAX = 10;
    private int num = 1;

    private final Semaphore oddSemaphore = new Semaphore(1);  // Odd gets to run first
    private final Semaphore evenSemaphore = new Semaphore(0); // Even waits

    public static void main(String[] args) {
        EvenOddUsingSemaphore printer = new EvenOddUsingSemaphore();

        Thread oddThread = new Thread(printer::printOdd, "Odd-Thread");
        Thread evenThread = new Thread(printer::printEven, "Even-Thread");

        oddThread.start();
        evenThread.start();
    }

    public void printOdd() {
        while (num <= MAX) {
            try {
                oddSemaphore.acquire();  // Wait for turn
                if (num % 2 != 0) {
                    System.out.println("Odd: " + num + " by " + Thread.currentThread().getName());
                    num++;
                }
                evenSemaphore.release(); // Allow even to run
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printEven() {
        while (num <= MAX) {
            try {
                evenSemaphore.acquire();  // Wait for turn
                if (num % 2 == 0) {
                    System.out.println("Even: " + num + " by " + Thread.currentThread().getName());
                    num++;
                }
                oddSemaphore.release();  // Allow odd to run
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
