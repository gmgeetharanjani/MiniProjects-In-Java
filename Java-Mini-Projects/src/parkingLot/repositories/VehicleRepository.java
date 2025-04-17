package parkingLot.repositories;

import parkingLot.models.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {
    private final Map<Long, Vehicle> vehicleMap = new HashMap<>();
    public Optional<Vehicle> findByVehicleNumber(String vehicleNumber) {
        for (Vehicle vehicle : vehicleMap.values()) {
            if (vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }

    public Vehicle save(Vehicle vehicle) {
        vehicle.setId(vehicleMap.size() + 1L);
        vehicleMap.put(vehicle.getId(), vehicle);
        return vehicle;
    }
}
