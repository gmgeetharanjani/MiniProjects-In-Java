package parkingLot.repositories;
import parkingLot.models.*;
import parkingLot.strategies.NearestSpotAssignmentStrategy;

import java.util.Map;
import java.util.HashMap;

import java.util.List;
import java.util.ArrayList;



public class ParkingLotRepository {
    private final Map<Long, ParkingLot> parkingLotMap = new HashMap<>();

    public ParkingLot findByGate(Gate gate) {
        // temporary code to simulate the database
        if (parkingLotMap.isEmpty()) {
            ParkingLot parkingLot = new ParkingLot();
            List<Gate> gates = new ArrayList<>();
            gates.add(gate);
            parkingLot.setGates(gates);
            parkingLot.setSpotAllotmentStrategyType(SpotAllotmentStrategyType.NEAREST_SPOT_ASSIGNMENT);
            parkingLotMap.put(1L,parkingLot);
        }
        for (ParkingLot parkingLot : parkingLotMap.values()) {
            if (parkingLot.getGates().contains(gate)) {
                return parkingLot;
            }
        }
        return null;
    }
}
