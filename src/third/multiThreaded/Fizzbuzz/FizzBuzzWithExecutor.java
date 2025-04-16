package third.multiThreaded.Fizzbuzz;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author trinapal
 */
public class FizzBuzzWithExecutor {

    private AtomicInteger num = new AtomicInteger(1);
    private static final int MAX = 15;
    Semaphore semaphore1 = new Semaphore(0);
    Semaphore semaphore2 = new Semaphore(0);
    Semaphore semaphore3 = new Semaphore(0);
    Semaphore semaphore4 = new Semaphore(1);
    public static void main(String[] args) {
        FizzBuzzWithExecutor fizzBuzzWithExecutor = new FizzBuzzWithExecutor();
        ExecutorService executors = Executors.newFixedThreadPool(4);
        executors.submit(fizzBuzzWithExecutor::printNumber);
        executors.submit(fizzBuzzWithExecutor::printFizzBuzz);
        executors.submit(fizzBuzzWithExecutor::printBuzz);
        executors.submit(fizzBuzzWithExecutor::printFizz);

        // alone not suitable as it might end up tasks abruptly.
        executors.shutdown();
        try {
            if(!executors.awaitTermination(5, TimeUnit.SECONDS)){
                executors.shutdownNow();
            }
        } catch (InterruptedException e) {
            executors.shutdownNow(); // Force shutdown if interrupted
            Thread.currentThread().interrupt();
        }
    }

    private void printFizz() {
        while(num.get() <= MAX){
            try {
                semaphore1.acquire();
                if(num.get() > MAX) break;
                System.out.println("fizz");
                releaseNext();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void printBuzz() {
        while(num.get() <= MAX){
            try {
                semaphore2.acquire();
                if(num.get() > MAX) break;
                System.out.println("buzz");
                releaseNext();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void printFizzBuzz() {
        while(num.get() <= MAX){
            try {
                semaphore3.acquire();
                if(num.get() > MAX) break;
                System.out.println("fizzbuzz");
                releaseNext();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    private void releaseNext() {
        num.incrementAndGet();
        semaphore4.release(); // to check the next number

    }

    //single controller to decide which method to pick
    private void printNumber() {
        while(num.get() <= MAX) {
            try {
                semaphore4.acquire();
                if(num.get() > MAX) break;
                if(num.get() % 5 ==0 && num.get() %3== 0){
                    semaphore3.release();
                }
                else if(num.get() % 5 ==0 ){
                    semaphore2.release();
                }
                else if(num.get() %3== 0){
                    semaphore1.release();
                }else{
                    System.out.println(num.get());
                    releaseNext();
                }

            }catch(InterruptedException interruptedException){
                Thread.currentThread().interrupt();
            }
        }
        //release other threads
        semaphore3.release();
        semaphore2.release();
        semaphore1.release();
    }
}
