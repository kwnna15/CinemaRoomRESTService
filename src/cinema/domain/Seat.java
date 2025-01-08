package cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record Seat(int row, int column, int price, @JsonIgnore boolean taken) {
}
