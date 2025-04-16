package third.parkinglot;

import third.parkinglot.VehicleSize;

/**
 * @author trinapal
 */
public class Car implements Vehicle {


    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }
}
