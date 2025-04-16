package third.parkinglot;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;
/**
 * @author trinapal
 */
public class ParkingLotDesignTesting {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance(); //singleton class
        //implements threading for each vehicle
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        for(int i =0; i <18; i++){ //total 18 vehicles are there at parking lot
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    //randomly vehicles will come
                    Random random = new Random();
                    int vehicleType = random.nextInt(3);
                    VehicleFactory vehicleFactory;
                    Vehicle vehicle;
                    switch (vehicleType){
                        case 0 :
                            vehicleFactory = new CarVehicleFactory();
                            vehicle = vehicleFactory.createVehicle();
                            break;
                        case 1:
                            vehicleFactory = new TruckVehicleactory();
                            vehicle = vehicleFactory.createVehicle();
                            break;
                        case 2:
                        default:
                            vehicleFactory = new MotorcycleVehicleFactory();
                            vehicle = vehicleFactory.createVehicle();
                            break;
                    }
                    boolean vehicleParked = parkingLot.parkVehicle(vehicle);
                    if (vehicleParked) {
                        System.out.println(vehicle.getClass().getSimpleName() + " parked successfully.");
                        try {
                            // Simulate the vehicle staying for a random amount of time (e.g., 1-5 seconds)
                            Thread.sleep(random.nextInt(5000) + 1000);  // Random time between 1 and 6 seconds
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); // Handle interruption
                        }
                        if (parkingLot.isOccupied(vehicle.getSize())) {
                            parkingLot.freeUpParkingSpot(vehicle);
                        }
                    } else {
                        System.out.println("No available space for " + vehicle.getClass().getSimpleName());
                    }
                }
            });
        }
        executorService.shutdown();

        /*for (int i = 0; i<7; i++){
            VehicleFactory carFactory = new CarVehicleFactory();
            Vehicle  car = carFactory.createVehicle();
            boolean isCarParked = parkingLot.parkVehicle(car);
            System.out.println("Car parked: " + isCarParked);
        } */


    }
}
