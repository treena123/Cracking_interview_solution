package third.multiThreaded.printABC;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author trinapal
 */
public class PrintABCWith3Threads {
     Semaphore semA = new Semaphore(1); // it will start first
     Semaphore semB = new Semaphore(0);
     Semaphore semC = new Semaphore(0);
     private static final int MAX = 10;
     AtomicInteger num = new AtomicInteger(0);
    public static void main(String[] args) {
        PrintABCWith3Threads printObj = new PrintABCWith3Threads();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(printObj::printA); //method reference
        executorService.execute(printObj::printB);
        executorService.execute(printObj::printC);

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }

    //another way is to implement  round robin
    private void printC() {
        while(num.get() <= MAX){
            try {
                semC.acquire();
                if(num.get() >= MAX) break;
                System.out.println("C");
                num.incrementAndGet();
                semA.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        semA.release();
    }

    private void printB() {
        while(num.get() <= MAX){
            try {
                semB.acquire();
                if(num.get() >= MAX) break;
                System.out.println("B");
                num.incrementAndGet();
                semC.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        semC.release();
    }

    // As this semA has the privilege to start first, this method will be the single controller
    private void printA() {
        while(true){
            try {
                semA.acquire();
                if(num.get() >= MAX) break;
                System.out.println("A");
                num.incrementAndGet();
                semB.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        semB.release();

    }

    /*private void printC() {
        while(num.get() <= MAX){
            try {
                semC.acquire();
                if(num.get() > MAX) break;
                System.out.println("C");
                releaseNext();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void printB() {
        while(num.get() <= MAX){
            try {
                semB.acquire();
                if(num.get() > MAX) break;
                System.out.println("B");
                releaseNext();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // As this semA has the privilege to start first, this method will be the single controller
    private void printA() {
        while(num.get() <= MAX){
            try {
                semA.acquire();
                if(num.get() > MAX) break;
                if(num.get() %3 == 2) semB.release();
                else if (num.get()%3 ==0) {
                    semC.release();
                }else{
                    System.out.println("A");
                    releaseNext();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        semB.release();
        semC.release();
    } */

    private void releaseNext() {
        num.incrementAndGet();
        semA.release();
    }
}
