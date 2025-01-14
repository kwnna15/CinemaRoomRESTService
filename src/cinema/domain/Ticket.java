package cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder(toBuilder = true)
public record Ticket(@JsonIgnore Token token, int row, int column, int price, @JsonIgnore boolean taken) implements Comparable<Ticket> {

    @Override
    public int compareTo(Ticket ticket) {
        var rowCompare = Integer.compare(row, ticket.row());
        if (rowCompare == 0) {
            return Integer.compare(column, ticket.column());
        }
        return rowCompare;
    }
}
