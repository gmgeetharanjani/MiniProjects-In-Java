package parkingLot.models;

import java.util.List;

public class ParkingLot {
    private List<ParkingFloor> parkingFloors;
    private List<Gate> gates;
    private List<VehicleType> vehicleTypes;
    private SpotAllotmentStrategyType spotAllotmentStrategyType;
    private FeesCalculatorStrategyType feesCalculatorStrategyType;
    private ParkingLotStatus parkingLotStatus;

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public SpotAllotmentStrategyType getSpotAllotmentStrategyType() {
        return spotAllotmentStrategyType;
    }

    public void setSpotAllotmentStrategyType(SpotAllotmentStrategyType spotAllotmentStrategyType) {
        this.spotAllotmentStrategyType = spotAllotmentStrategyType;
    }

    public FeesCalculatorStrategyType getFeesCalculatorStrategyType() {
        return feesCalculatorStrategyType;
    }

    public void setFeesCalculatorStrategyType(FeesCalculatorStrategyType feesCalculatorStrategyType) {
        this.feesCalculatorStrategyType = feesCalculatorStrategyType;
    }

    public ParkingLotStatus getParkingLotStatus() {
        return parkingLotStatus;
    }

    public void setParkingLotStatus(ParkingLotStatus parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }
}
