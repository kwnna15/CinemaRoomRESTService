package cinema.domain;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
public record Cinema(int rows, int columns, List<Seat> seats) {
}
