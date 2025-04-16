package second.reentrantLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author trinapal
 */
public  class PricesContainer {
    public Lock getObjectLock() {
        return objectLock;
    }

    public void setObjectLock(Lock objectLock) {
        this.objectLock = objectLock;
    }

    public double getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(double bitcoin) {
        this.bitcoin = bitcoin;
    }

    public double getEtherPrice() {
        return etherPrice;
    }

    public void setEtherPrice(double etherPrice) {
        this.etherPrice = etherPrice;
    }

    public double getLitecoinPrice() {
        return litecoinPrice;
    }

    public void setLitecoinPrice(double litecoinPrice) {
        this.litecoinPrice = litecoinPrice;
    }

    public double getBitcoinCashPrice() {
        return bitcoinCashPrice;
    }

    public void setBitcoinCashPrice(double bitcoinCashPrice) {
        this.bitcoinCashPrice = bitcoinCashPrice;
    }

    public double getRipplePrice() {
        return ripplePrice;
    }

    public void setRipplePrice(double ripplePrice) {
        this.ripplePrice = ripplePrice;
    }

    private Lock objectLock = new ReentrantLock();
    /*
    these are shared variables
     */
    private double bitcoin;
    private double etherPrice;
    private double litecoinPrice;
    private double bitcoinCashPrice;
    private double ripplePrice;


}

class PricesUpdater extends Thread{
    PricesContainer pricesContainer;

    //Simulating the object call
    private Random random = new Random();
    PricesUpdater(PricesContainer pricesContainer){
        this.pricesContainer = pricesContainer;
    }
    @Override
    public void run()  {
        // update the prices
        while(true){
            pricesContainer.getObjectLock().lock();
            try{
            try {
                Thread.sleep(1000); //assuming network call
            }catch (InterruptedException ex) {
            }
                pricesContainer.setEtherPrice(random.nextInt(2000));
                pricesContainer.setBitcoin(random.nextInt(2000));
                pricesContainer.setLitecoinPrice(random.nextInt(500));
                pricesContainer.setRipplePrice(random.nextInt(5000));
                pricesContainer.setBitcoinCashPrice(random.nextInt(2000));
            }finally {
                pricesContainer.getObjectLock().unlock();
            }
        try {
            Thread.sleep(1000); //assuming network call
        }catch (InterruptedException ex) {
        }
        }
    }
}
