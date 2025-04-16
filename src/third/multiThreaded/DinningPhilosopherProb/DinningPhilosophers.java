package third.multiThreaded.DinningPhilosopherProb;

import java.util.concurrent.Semaphore;

/**
 * @author trinapal
 */
public class DinningPhilosophers extends Thread{
    private final int id;
    private final Semaphore leftFork;
    private final Semaphore rightFork;
    private final Semaphore limit;

    DinningPhilosophers(int id, Semaphore leftFork, Semaphore rightFork, Semaphore limit){
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.limit = limit;
    }
    @Override
    public void run() {
        try{
            while(true){
                think();
                limit.acquire();
                leftFork.acquire();
                rightFork.acquire();
                eat();
                leftFork.release();
                rightFork.release();
                limit.release();
            }
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
        final int NUM = 5 ; //number of philosopher
        Semaphore[] forks = new Semaphore[NUM]; //binary
        for (int i = 0; i < NUM; i++) {
            forks[i] = new Semaphore(1);
        }
        Semaphore limit = new Semaphore(NUM - 1); // Prevent deadlock only allowing 4 philosophers at a time

        for (int i = 0; i < NUM; i++) {
            Semaphore left = forks[i];
            Semaphore right = forks[(i + 1) % NUM];
            new DinningPhilosophers(i, left, right, limit).start();
        }
    }
}
