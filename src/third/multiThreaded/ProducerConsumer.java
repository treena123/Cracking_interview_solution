package third.multiThreaded;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author trinapal
 */
public class ProducerConsumer {
    private static final int CAPACITY = 15;
    private final Queue<Integer> queue = new LinkedList<>();
    private final Object lock = new Object();
    private int value = 0;

    public void produce() throws InterruptedException {
        while(true){
            synchronized (lock){
                while(queue.size() == CAPACITY){
                    System.out.println("Producer waiting...");
                    lock.wait(); // wait if full
                }
                queue.offer(value++);
                System.out.println("Produced: " + (value - 1));
                lock.notify();

            }
            Thread.sleep(500); // simulate time to produce
        }
    }

    public void consume() throws InterruptedException{
        while (true) {
            synchronized (lock) {
                while (queue.isEmpty()) {
                    System.out.println("Consumer waiting...");
                    lock.wait(); // wait if empty
                }
                int consumed = queue.poll();
                System.out.println("Consumed: " + consumed);
                lock.notify(); // notify producer
            }
            Thread.sleep(800); // simulate time to consume
        }
    }
    public static void main(String[] args) {

        ProducerConsumer producerConsumer = new ProducerConsumer();
        Thread producerThread = new Thread(() -> {
            try {
                producerConsumer.produce();
            } catch (InterruptedException ignored) {}
        });

        Thread consumerThread = new Thread(() -> {
            try {
                producerConsumer.consume();
            } catch (InterruptedException ignored) {}
        });
        producerThread.start();
        consumerThread.start();


    }
}
