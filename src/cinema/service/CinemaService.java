package cinema.service;

import cinema.domain.Cinema;
import cinema.domain.Seat;

import java.util.Optional;

public interface CinemaService {
    Cinema getCinema();

    Optional<Seat> purchaseSeat(int row, int column);

    boolean isValidSeat(int row, int column);
}
