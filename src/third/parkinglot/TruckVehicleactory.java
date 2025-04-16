package third.parkinglot;

/**
 * @author trinapal
 */
public class TruckVehicleactory extends VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new Truck();
    }
}
