package parkingLot.strategies;
import parkingLot.models.*;

import parkingLot.repositories.ParkingLotRepository;

public class NearestSpotAssignmentStrategy implements SpotAllotmentStrategy {
    private ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingSpot getParkingSpot(Gate gate, VehicleType vehicleType) {
        ParkingLot parkingLot = parkingLotRepository.findByGate(gate);
        for (ParkingFloor floor: parkingLot.getParkingFloors()) {
            for (ParkingSpot spot: floor.getParkingSpots()) {
                if (spot.getAllowedVehicleTypes().contains(vehicleType) && spot.getParkingSpotStatus() == ParkingSpotStatus.EMPTY) {
                    return spot;
                }
            }
        }
        return null;
    }
}
