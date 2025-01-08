package cinema.controller.error;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record ErrorResponse(String error) {
}
