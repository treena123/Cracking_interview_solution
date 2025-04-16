package third.multiThreaded.evenOdd;

/**
 * @author trinapal
 */
/*
Goal: One thread prints even numbers, the other prints odd numbers, in order.
 */
public class EventAndOdd {
    private static final int MAX = 12;
    private int num =1; //shared resource
    private final Object lock = new Object();
    public static void main(String[] args) {
        EventAndOdd printer = new EventAndOdd();
        Thread thread1 = new Thread(printer::printEven, "Even-Thread");
        Thread thread2 = new Thread(printer::printOdd, "Odd-Thread");
        thread1.start();
        thread2.start();
    }
    public void printEven(){
        while(num <=MAX){
            synchronized (lock) {
                if (num % 2 == 0) {
                    System.out.println("Even: " + num + " by " + Thread.currentThread().getName());
                    num++;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }
    }

    public void printOdd(){
        while(num <= MAX){
            synchronized (lock) {
                if (num % 2 != 0) {
                    System.out.println("Odd: " + num + " by " + Thread.currentThread().getName());
                    num++;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }
    }
}
