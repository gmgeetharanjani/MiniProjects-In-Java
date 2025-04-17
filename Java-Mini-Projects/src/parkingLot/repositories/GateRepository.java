package parkingLot.repositories;
import parkingLot.models.Gate;
import parkingLot.models.GateStatus;
import parkingLot.models.GateType;
import parkingLot.models.Operator;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

public class GateRepository {
    private final Map<Long, Gate> gateMap = new HashMap<>();


    public Optional<Gate> findByGateId(Long gateId) {
        // temporary code to simulate the database
        // In a real-world scenario, this would be replaced with actual database access code

        if (gateMap.isEmpty()) {
            gateMap.put(1L, new Gate(1L, GateStatus.OPEN, GateType.ENTRY, new Operator(101, "John Doe"), 1));
        }
        if (gateMap.containsKey(gateId)) {
            return Optional.of(gateMap.get(gateId));
        }
        return Optional.empty();
    }
}
