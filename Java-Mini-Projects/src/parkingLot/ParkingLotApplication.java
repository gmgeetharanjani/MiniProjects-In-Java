package parkingLot;

import parkingLot.controllers.TicketController;
import parkingLot.dto.IssueTicketRequest;
import parkingLot.dto.IssueTicketResponse;
import parkingLot.dto.ResponseStatus;
import parkingLot.models.VehicleType;
import parkingLot.repositories.GateRepository;
import parkingLot.repositories.ParkingLotRepository;
import parkingLot.repositories.TicketRepository;
import parkingLot.repositories.VehicleRepository;
import parkingLot.service.TicketService;

public class ParkingLotApplication {
    public static void main(String[] args) {
        TicketController ticketController = getTicketController();

        // Example usage
        IssueTicketRequest request = new IssueTicketRequest();
        request.setVehicleNumber("ABC123");
        request.setOwnerName("John Doe");
        request.setVehicleType(VehicleType.CAR);
        request.setGateId(1L);
        IssueTicketResponse response = ticketController.issueTicket(request);
        if (response.getStatus() == ResponseStatus.SUCCESS) {
            System.out.println("Ticket issued successfully: " + response.getTicket());
        } else {
            System.out.println("Failed to issue ticket.");
        }
    }

    private static TicketController getTicketController() {
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
        );
        return new TicketController(ticketService);
    }
}
