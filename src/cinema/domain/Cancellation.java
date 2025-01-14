package cinema.domain;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record Cancellation(Ticket ticket) {
}
