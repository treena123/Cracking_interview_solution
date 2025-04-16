package third.parkinglot;

import third.parkinglot.VehicleSize;

/**
 * @author trinapal
 */
public class MotorCycle implements Vehicle {

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }
}
