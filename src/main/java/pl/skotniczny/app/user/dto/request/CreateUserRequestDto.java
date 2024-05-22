package pl.skotniczny.app.user.dto.request;

import lombok.Builder;

@Builder
public record CreateUserRequestDto(String username, String password) {
}
