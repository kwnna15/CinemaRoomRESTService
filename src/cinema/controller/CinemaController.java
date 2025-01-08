package cinema.controller;

import cinema.domain.Cinema;
import cinema.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CinemaController {
    private final CinemaService service;

    @GetMapping(path = "/seats")
    public ResponseEntity<Cinema> getSeats(){
        var cinema = service.getCinema();
        return ResponseEntity.ok(cinema);
    }
}
