package third.multiThreaded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQueue {
    private static final int CAPACITY = 5;
    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(CAPACITY);
    private int value = 0;

    public void produce() throws InterruptedException {
        while (true) {
            queue.put(value); // blocks if queue is full
            System.out.println("Produced: " + value);
            value++;
            Thread.sleep(500); // simulate time to produce
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            int consumed = queue.take(); // blocks if queue is empty
            System.out.println("Consumed: " + consumed);
            Thread.sleep(800); // simulate time to consume
        }
    }

    public static void main(String[] args) {
        ProducerConsumerBlockingQueue pc = new ProducerConsumerBlockingQueue();

        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException ignored) {}
        });

        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException ignored) {}
        });

        producerThread.start();
        consumerThread.start();
    }
}
