package parkingLot.controllers;

import parkingLot.dto.IssueTicketRequest;
import parkingLot.dto.IssueTicketResponse;
import parkingLot.dto.ResponseStatus;
import parkingLot.models.Ticket;
import parkingLot.service.TicketService;

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponse issueTicket(IssueTicketRequest request) {
        IssueTicketResponse response = new IssueTicketResponse();
        try {
            Ticket ticket = ticketService.issueTicket(request.getVehicleNumber(), request.getOwnerName(),
                    request.getVehicleType(), request.getGateId());
            response.setTicket(ticket);
            response.setStatus(ResponseStatus.SUCCESS);
            return response;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            response.setStatus(ResponseStatus.FAILURE);
            return response;
        }
    }
}
