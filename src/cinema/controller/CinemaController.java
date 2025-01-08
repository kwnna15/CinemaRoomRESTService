package cinema.controller;

import cinema.controller.error.ErrorResponse;
import cinema.domain.Cinema;
import cinema.domain.Seat;
import cinema.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CinemaController {
    private final CinemaService service;

    @GetMapping(path = "/seats")
    public ResponseEntity<Cinema> getSeats(){
        var cinema = service.getCinema();
        return ResponseEntity.ok(cinema);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat seat) {
        if (!service.isValidSeat(seat.row(), seat.column())){
            return ResponseEntity.badRequest()
                    .body(ErrorResponse.builder().error("The number of a row or a column is out of bounds!").build());
        }
        var purchasedSeat = service.purchaseSeat(seat.row(), seat.column());
        if (purchasedSeat.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().error("The ticket has been already purchased!").build());
        }
        return ResponseEntity.ok(purchasedSeat);
    }
}
