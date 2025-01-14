package cinema.controller;

import cinema.controller.error.ErrorResponse;
import cinema.domain.Cinema;
import cinema.domain.Ticket;
import cinema.domain.Token;
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
    public ResponseEntity<Cinema> getSeats() {
        var cinema = service.getCinema();
        return ResponseEntity.ok(cinema);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Ticket ticket) {
        if (!service.isValidSeat(ticket.row(), ticket.column())) {
            return ResponseEntity.badRequest()
                    .body(ErrorResponse.builder().error("The number of a row or a column is out of bounds!").build());
        }
        var purchaseTicket = service.purchaseTicket(ticket.row(), ticket.column());
        if (purchaseTicket.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().error("The ticket has been already purchased!").build());
        }
        return ResponseEntity.ok(purchaseTicket);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Token token) {
        var maybeCancellation = service.returnTicket(token);
        if (maybeCancellation.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ErrorResponse.builder().error("Wrong token!").build());
        }
        return ResponseEntity.ok(maybeCancellation.get());
    }
}
