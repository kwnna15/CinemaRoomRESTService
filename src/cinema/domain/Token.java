package cinema.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Builder
public record Token(@JsonAlias("token") UUID id) {
    public static Token random() {
        return new Token(UUID.randomUUID());
    }
}
