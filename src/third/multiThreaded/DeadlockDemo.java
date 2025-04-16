package third.multiThreaded;

import java.util.logging.Logger;

/**
 * @author trinapal
 */
public class DeadlockDemo {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    Logger logger = Logger.getLogger("DeadlockDemo");
    public void methodA(){
        /*synchronized (lock1){
            logger.info("thread 1 lock acquired");
            try{
                Thread.sleep(1000);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
            synchronized (lock2){
                logger.info("thread 2 lock acquired");
            }
        }*/
        orderedLocks(lock1, lock2);
    }

    private void orderedLocks(Object lock2, Object lock1) {
        synchronized (lock1){
            logger.info("thread 2 lock acquired");
        }try {
            Thread.sleep(50);
        } catch (InterruptedException ignored) {

        }
        synchronized (lock2){
            logger.info("thread 2 lock acquired");
        }
    }

    public void methodB(){
        /*synchronized (lock2){
            logger.info("thread 2 lock acquired");
            try{
                Thread.sleep(1000);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
            synchronized (lock1){
                logger.info("thread 1 lock acquired");
            }
        }*/
        orderedLocks(lock1, lock2);
    }
    public static void main(String[] args) {
        DeadlockDemo deadlockDemo = new DeadlockDemo();
        Thread t1 = new Thread(deadlockDemo::methodA);
        Thread t2 = new Thread(deadlockDemo::methodB);
        t1.start();
        t2.start();
    }
}
