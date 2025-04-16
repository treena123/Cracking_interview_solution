package third.parkinglot;

/**
 * @author trinapal
 */

/*
This interface is responsible for the parking menthod and payment method.
Different types of vehicles will implement this methods and logic will be updated accordingly
 */
public interface Vehicle {

    VehicleSize getSize();
}


enum VehicleSize{
    SMALL,MEDIUM,LARGE
}