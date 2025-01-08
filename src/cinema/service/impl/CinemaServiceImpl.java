package cinema.service.impl;

import cinema.domain.Cinema;
import cinema.domain.Seat;
import cinema.service.CinemaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Override
    public Cinema getCinema() {
        var seats = new ArrayList<Seat>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                seats.add(Seat.builder()
                        .row(i)
                        .column(j)
                        .price(10)
                        .build());
            }
        }
        return Cinema.builder()
                .rows(9)
                .columns(9)
                .seats(seats)
                .build();
    }
}
