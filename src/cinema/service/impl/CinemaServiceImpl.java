package cinema.service.impl;

import cinema.domain.Cinema;
import cinema.domain.Seat;
import cinema.service.CinemaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {

    private static final int ROW_SIZE = 9;
    private static final int COLUMN_SIZE = 9;

    private final Cinema cinema;

    public CinemaServiceImpl() {
        this.cinema = createCinema();
    }

    @Override
    public Cinema getCinema() {
        return cinema;
    }

    @Override
    public Optional<Seat> purchaseSeat(int row, int column) {
        var index = (row - 1) * COLUMN_SIZE + column - 1;
        var seat = cinema.seats().get(index);
        if (seat.taken()) {
            return Optional.empty();
        }
        var updatedSeat = seat.toBuilder().taken(true).build();
        cinema.seats().set(index, updatedSeat);
        return Optional.of(updatedSeat);
    }

    @Override
    public boolean isValidSeat(int row, int column) {
        return (row <= ROW_SIZE && row >= 1) && (column <= COLUMN_SIZE && column >= 1);
    }

    private static Cinema createCinema() {
        var seats = new ArrayList<Seat>();
        for (int i = 1; i <= ROW_SIZE; i++) {
            int price = (i < 5) ? 10 : 8;
            for (int j = 1; j <= COLUMN_SIZE; j++) {
                seats.add(Seat.builder()
                        .row(i)
                        .column(j)
                        .price(price)
                        .build());
            }
        }
        return Cinema.builder()
                .rows(ROW_SIZE)
                .columns(COLUMN_SIZE)
                .seats(seats)
                .build();
    }
}
