package parkingLot.strategies;

import parkingLot.models.SpotAllotmentStrategyType;

public class SpotAllotmentStrategyFactory {
    public static SpotAllotmentStrategy getSpotAllotmentStrategy(SpotAllotmentStrategyType spotAllotmentStrategyType) {
        return new NearestSpotAssignmentStrategy();
    }
}
