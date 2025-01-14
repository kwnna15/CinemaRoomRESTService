package cinema.service.impl;

import cinema.domain.*;
import cinema.repository.TicketRepository;
import cinema.service.CinemaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {

    private static final int ROW_SIZE = 9;
    private static final int COLUMN_SIZE = 9;

    private final TicketRepository ticketRepository;

    public CinemaServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        createTickets();
    }

    @Override
    public Cinema getCinema() {
        var tickets = ticketRepository.getAllTickets()
                .stream()
                .sorted()
                .toList();

        return Cinema.builder()
                .rows(ROW_SIZE)
                .columns(COLUMN_SIZE)
                .tickets(tickets)
                .build();
    }

    @Override
    public Optional<Booking> purchaseTicket(int row, int column) {
        var ticket = ticketRepository.getAllTickets()
                .stream()
                .filter(t -> t.row() == row && t.column() == column)
                .findFirst();

        if (ticket.isPresent()) {
            if (ticket.get().taken()) {
                return Optional.empty();
            }
            var purchsedTicket = ticket.get().toBuilder().taken(true).build();
            ticketRepository.saveTicket(purchsedTicket);
            return Optional.of(Booking.builder()
                    .token(purchsedTicket.token().id())
                    .ticket(purchsedTicket)
                    .build());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Cancellation> returnTicket(Token token) {
        var ticket = ticketRepository.getTicket(token);

        if (ticket.isPresent()) {
            if (!ticket.get().taken()) {
                return Optional.empty();
            }

            var cancelledTicket = ticket.get().toBuilder().taken(false).build();
            ticketRepository.saveTicket(cancelledTicket);
            return Optional.of(Cancellation.builder()
                    .ticket(cancelledTicket)
                    .build());
        }

        return Optional.empty();
    }

    @Override
    public boolean isValidSeat(int row, int column) {
        return (row <= ROW_SIZE && row >= 1) && (column <= COLUMN_SIZE && column >= 1);
    }

    private void createTickets() {
        for (int i = 1; i <= ROW_SIZE; i++) {
            int price = (i < 5) ? 10 : 8;
            for (int j = 1; j <= COLUMN_SIZE; j++) {
                ticketRepository.saveTicket(Ticket.builder()
                        .token(Token.random())
                        .row(i)
                        .column(j)
                        .price(price)
                        .build());
            }
        }
    }
}
