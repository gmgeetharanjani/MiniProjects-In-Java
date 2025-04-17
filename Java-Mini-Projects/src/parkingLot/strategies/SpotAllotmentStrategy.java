package parkingLot.strategies;

import parkingLot.models.ParkingSpot;
import parkingLot.models.VehicleType;
import parkingLot.models.Gate;

public interface SpotAllotmentStrategy {
    ParkingSpot getParkingSpot(Gate gate , VehicleType vehicleType);
}
