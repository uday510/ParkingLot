package repositories;

import models.Ticket;

import java.util.Map;
import java.util.TreeMap;

public class TicketRepository {
    private Map<Long, Ticket> tickets = new TreeMap<>();
    private long previousId = 0;
    public Ticket saveTicket(Ticket ticket) {
        ++previousId;
        ticket.setId(previousId);
        tickets.put(previousId, ticket);

        return ticket;
    }
}
