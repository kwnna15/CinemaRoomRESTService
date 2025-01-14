package cinema.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
public record Cinema(int rows, int columns, @JsonProperty("seats") List<Ticket> tickets) {
}
