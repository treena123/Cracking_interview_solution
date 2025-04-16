package third.multiThreaded.Fizzbuzz;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Four threads print fizz, buzz, fizzbuzz, and numbers respectively, in order.
 */
public class FizzbuzzWith4Thread {
    Semaphore semaphore1 = new Semaphore(0);
    Semaphore semaphore2 = new Semaphore(0);
    Semaphore semaphore3 = new Semaphore(0);
    Semaphore semaphore4 = new Semaphore(1);
    //static int num = 1;
    static AtomicInteger num = new AtomicInteger(1);
    static final int MAX = 15;

    public static void main(String[] args) {
        FizzbuzzWith4Thread fizzbuzz = new FizzbuzzWith4Thread();
        Thread t1 = new Thread(fizzbuzz::printFizz, "t1");
        Thread t2 = new Thread(fizzbuzz::printBuzz, "t2");
        Thread t3 = new Thread(fizzbuzz::printFizzBuzz, "t3");
        Thread t4 = new Thread(fizzbuzz::printNumber, "t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private void printNumber() {
        while (true) {
            try {
                semaphore4.acquire();
                if (num.get() > MAX) break; // Stop processing if max is reached

                if (num.get() % 5 == 0 && num.get() % 3 == 0) {
                    semaphore3.release();
                } else if (num.get() % 5 == 0) {
                    semaphore2.release();
                } else if (num.get() % 3 == 0) {
                    semaphore1.release();
                } else {
                    System.out.println(num);
                    releaseNext();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // 🔴 Ensure all threads get released so they don't block forever
        semaphore1.release();
        semaphore2.release();
        semaphore3.release();
    }


    private void printFizzBuzz() {
        while (true) {
            try {
                semaphore3.acquire();
                if (num.get() > MAX) break;
                System.out.println("fizzbuzz");
                releaseNext();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void printBuzz() {
        while (true) {
            try {
                semaphore2.acquire();
                if (num.get() > MAX) break;
                System.out.println("buzz");
                releaseNext();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void printFizz() {
        while (true) {
            try {
                semaphore1.acquire();
                if (num.get() > MAX) break;
                System.out.println("fizz");
                releaseNext();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void releaseNext() {
        //num++; // improvement needed- use Atomic integer
        num.incrementAndGet();
        if (num.get() > MAX) return;
        semaphore4.release();
    }
}
