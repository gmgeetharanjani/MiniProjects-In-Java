package parkingLot.repositories;

import parkingLot.models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    Map<Long, Ticket> ticketMap = new HashMap<>();
    public Ticket save(Ticket ticket) {
        ticket.setId(ticketMap.size() + 1L);
        ticketMap.put(ticket.getId(), ticket);
        return ticket;
    }
}
