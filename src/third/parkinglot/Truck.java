package third.parkinglot;

import java.util.Random;

/**
 * @author trinapal
 */
public class Truck implements  Vehicle{
    //private ParkingLot parkingLot;
    private long entryTime;
    private long exitTime;
    Random random = new Random();
    Truck(){
         entryTime = System.currentTimeMillis();
         //simulate the existTime using random
        exitTime = random.nextLong(500);
    }


    @Override
    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }
}
