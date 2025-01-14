package cinema.service;

import cinema.domain.Booking;
import cinema.domain.Cancellation;
import cinema.domain.Cinema;
import cinema.domain.Token;

import java.util.Optional;

public interface CinemaService {
    Cinema getCinema();

    Optional<Booking> purchaseTicket(int row, int column);

    Optional<Cancellation> returnTicket(Token token);

    boolean isValidSeat(int row, int column);
}
