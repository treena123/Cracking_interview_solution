package third.parkinglot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author trinapal
 */
public class ParkingLot {
    private static ParkingLot instance;
    private Vehicle vehicle;
    // Assume this list holds parking spots and their sizes
    private Map<VehicleSize,Integer> parkingSpots;
    static int capacity = 100;
    ReentrantLock reentrantLock = new ReentrantLock(true);
    public Queue<Vehicle> waitingCarQueue = new LinkedList<>();
    private final Condition spaceAvailableCondition = reentrantLock.newCondition();

    public static ParkingLot getInstance(){
        if(instance == null){
            instance = new ParkingLot();
        }
        return instance;
    }

    private ParkingLot(){
        parkingSpots = new HashMap<>();
        parkingSpots.put(VehicleSize.SMALL,5);
        parkingSpots.put(VehicleSize.MEDIUM,5);
        parkingSpots.put(VehicleSize.LARGE,5);
    }
    /*Separation of concern applied */

    public boolean isOccupied(VehicleSize vehicleSize){
        return parkingSpots.get(vehicleSize) ==0;
    }
    //logic for parking
    //public synchronized boolean parkVehicle(Vehicle vehicle){
    public  boolean parkVehicle(Vehicle vehicle){
        reentrantLock.lock();
        try {
            VehicleSize size = vehicle.getSize();
            int spotNumber = 0;

            while(isOccupied(vehicle.getSize())){
                try {
                    //put the vehicle in the queue
                    waitingCarQueue.add(vehicle);
                    spaceAvailableCondition.await(4, TimeUnit.SECONDS);
                }catch(InterruptedException ex){
                    Thread.currentThread().interrupt(); // Handle the interruption
                }
            }
            // first check if spot is available
            if (!isOccupied(vehicle.getSize())) {

                //get the parking spot
                //try {
                spotNumber = getAvailableSpot(size);
                //reduce the parking spot number
                parkingSpots.put(size, parkingSpots.get(size) - 1);


                System.out.println(vehicle.getClass().getSimpleName() + " is parked at " + spotNumber + " and available spot is " + parkingSpots.get(size));
                return true;
            } else {
                System.out.println("There are no spots available");

                return false;
            }
        } finally {
        reentrantLock.unlock();
        }
    }

    private int getAvailableSpot(VehicleSize size) {
        return parkingSpots.get(size);
    }

    /// Method to free up a parking spot when a vehicle leaves
    public void freeUpParkingSpot(Vehicle vehicle){
        reentrantLock.lock();
        try {
            VehicleSize size = vehicle.getSize();
            parkingSpots.put(size, getAvailableSpot(size) + 1);
            System.out.println(vehicle.getClass().getSimpleName() + " left. Spot freed.");
            // check if any vehicles are waiting
            if (!waitingCarQueue.isEmpty()) {
                Vehicle nextVehicle =  waitingCarQueue.poll();
                System.out.println("Notifying " + nextVehicle.getClass().getSimpleName() + " that a spot is available.");
                spaceAvailableCondition.signal();  // Notify one waiting car
            }
        }finally{
            reentrantLock.unlock();
        }
    }



}
