package third.multiThreaded.DinningPhilosopherProb;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author trinapal
 */
public class DinniongPhilosopherUsingAsymmetricForkPicking extends Thread{
        private int id;
        private Semaphore rightFork;
        private Semaphore leftFork;

    DinniongPhilosopherUsingAsymmetricForkPicking( int id, Semaphore leftFork, java.util.concurrent.Semaphore
        rightFork){
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        @Override
        public void run () {
            try {
                think();
                //check th even or odd
                if(id %2 == 0){
                    leftFork.acquire();
                    rightFork.acquire();
                }else{
                    rightFork.acquire();
                    leftFork.acquire();
                }

                eat();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        private void think () throws InterruptedException {
            System.out.println("Philosopher " + id + " is thinking");
            Thread.sleep((long) (Math.random() * 1000)); // after 1 sec , it will sleep
        }
        private void eat () throws InterruptedException {
            System.out.println("Philosopher " + id + " is eating");
            Thread.sleep((long) (Math.random() * 1000));
        }

        public static void main (String[]args){
           Semaphore[] forks = new Semaphore[5];
            final int NUM = 5;
            for (int i = 0; i < NUM; i++) {
                forks[i] = new Semaphore(1);
            }
            /*Even-indexed philosophers pick left first, then right.

              Odd-indexed philosophers pick right first, then left.
             */
            for (int i = 0; i < NUM; i++) {
                Semaphore left = forks[i];
                Semaphore right = forks[(i + 1) % NUM];
                new DinniongPhilosopherUsingAsymmetricForkPicking(i, left, right).start();
            }
        }

}
