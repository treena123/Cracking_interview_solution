package third.parkinglot;

/**
 * @author trinapal
 */
public class MotorcycleVehicleFactory extends VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new MotorCycle();
    }
}
