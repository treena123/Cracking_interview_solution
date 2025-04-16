package third.multiThreaded.DinningPhilosopherProb;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author trinapal
 */
public class DinningPhiloSopherUsingReentrant extends Thread{
    private int id;
    private Semaphore limit;
    private Semaphore rightFork;
    private Semaphore leftFork;
    ReentrantLock reentrantLock = new ReentrantLock(true);
    private final Condition forkAvailableCondition = reentrantLock.newCondition();

    DinningPhiloSopherUsingReentrant(int id, Semaphore leftFork, Semaphore rightFork, Semaphore limit){
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.limit = limit;
    }

    @Override
    public void run(){
        try {
            think();
            limit.acquire();
            // Ensure both forks are acquired together to prevent deadlock
            reentrantLock.lock();
            try {
                leftFork.acquire();
                rightFork.acquire();
            } finally {
                reentrantLock.unlock();
            }
            eat();
            limit.release();
            leftFork.release();
            rightFork.release();


        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking");
        Thread.sleep((long)(Math.random() * 1000)); // after 1 sec , it will sleep
    }
    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating");
        Thread.sleep((long)(Math.random() * 1000));
    }

    public static void main(String[] args) {
        Semaphore [] forks = new Semaphore[5];
        final int NUM = 5;
        for (int  i = 0; i<NUM; i++){
            forks[i] = new Semaphore(1);

        }
        Semaphore limit = new Semaphore(NUM - 1); // Prevent deadlock only allowing 4 philosophers at a time

        for (int i = 0; i < NUM; i++) {
            Semaphore left = forks[i];
            Semaphore right = forks[(i + 1) % NUM];
            new DinningPhiloSopherUsingReentrant(i, left, right, limit).start();
        }
    }
}
