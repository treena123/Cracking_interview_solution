package third.parkinglot;

/**
 * @author trinapal
 */
public class CarVehicleFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}
