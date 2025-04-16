package third.multiThreaded;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author trinapal
 */
public class MultithreadedCounter{
    /*Goal: Multiple threads increment a shared counter.*/
    AtomicInteger counter =  new AtomicInteger(0);
    private static final int MAX = 10;
    public static void main(String[] args) {
        MultithreadedCounter counterCheck = new MultithreadedCounter();
        CountDownLatch latch = new CountDownLatch(1);
        Runnable task =() -> {
            try{
                System.out.println(Thread.currentThread().getName() + " waiting...");
                latch.await(); // All threads wait here until latch.countDown() is called
                counterCheck.incrementCounter();

            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
        };
        Thread t1= new Thread(counterCheck::incrementCounter,"Thraed1");
        Thread t2= new Thread(counterCheck::incrementCounter,"Thraed2");
        Thread t3= new Thread(counterCheck::incrementCounter,"Thraed3");
        t1.start();
        t2.start();
        t3.start();

        // Simulate setup time or countdown
        try {
            Thread.sleep(1000);
            System.out.println("All threads starting now!");
            latch.countDown(); // Release all waiting threads at once
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public void incrementCounter(){ //another way to synchronized but then multiple threads can't process in parallel
        while(true) {
            int current = counter.get();
            if(current>= MAX){
                break;
            }
            int updatedCounter = current + 1;
            if(counter.compareAndSet(current,updatedCounter)) {
                try {
                    System.out.println(" count is " + counter.get() + "  updated by " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
