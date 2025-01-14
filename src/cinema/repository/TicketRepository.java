package cinema.repository;

import cinema.domain.Ticket;
import cinema.domain.Token;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TicketRepository {
    private final Map<Token, Ticket> tickets = new ConcurrentHashMap<>();

    public void saveTicket(Ticket ticket) {
        tickets.put(ticket.token(), ticket);
    }

    public Optional<Ticket> getTicket(Token token) {
        return Optional.ofNullable(tickets.get(token));
    }

    public List<Ticket> getAllTickets() {
        return tickets.values().stream().toList();
    }
}
