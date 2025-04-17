package parkingLot.service;
import parkingLot.models.*;

import java.util.Date;
import java.util.Optional;

import parkingLot.repositories.GateRepository;
import parkingLot.exceptions.GateNotFoundException;
import parkingLot.repositories.ParkingLotRepository;
import parkingLot.repositories.TicketRepository;
import parkingLot.repositories.VehicleRepository;
import parkingLot.strategies.SpotAllotmentStrategy;
import parkingLot.strategies.SpotAllotmentStrategyFactory;


public class TicketService {
    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }
    public Ticket issueTicket(String vehicleNumber, String ownerName, VehicleType vehicleType, Long gateId) throws GateNotFoundException {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());
        Optional<Gate> optionalGate = gateRepository.findByGateId(gateId);
        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Gate not found with ID: " + gateId);
        }
        Gate gate = optionalGate.get();
        ticket.setGate(gate);
        Vehicle savedVehicle;
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if (optionalVehicle.isPresent()) {
            savedVehicle = optionalVehicle.get();
        } else {
            Vehicle vehicle = new Vehicle(vehicleNumber, ownerName, vehicleType);
            savedVehicle = vehicleRepository.save(vehicle);
        }
        ticket.setVehicle(savedVehicle);
        ticket.setGeneratedBy(gate.getOperator());
        ticket.setNumber("TicketNo" + System.currentTimeMillis());

        SpotAllotmentStrategyType spotAllotmentStrategyType = parkingLotRepository.findByGate(gate).getSpotAllotmentStrategyType();
        SpotAllotmentStrategy spotAllotmentStrategy = SpotAllotmentStrategyFactory.getSpotAllotmentStrategy(spotAllotmentStrategyType);
        ParkingSpot parkingSpot = spotAllotmentStrategy.getParkingSpot(gate, vehicleType);
        ticket.setParkingSpot(parkingSpot);
        return ticketRepository.save(ticket);
    }
}
