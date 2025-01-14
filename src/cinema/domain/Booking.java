package cinema.domain;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Builder(toBuilder = true)
public record Booking(UUID token, Ticket ticket) {
}
