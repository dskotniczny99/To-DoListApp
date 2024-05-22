package pl.skotniczny.app.user.dto.response;

import lombok.Builder;

@Builder
public record CreateUserDto(String username, String password, boolean isCreated) {
}
