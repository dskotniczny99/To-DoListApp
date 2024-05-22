package pl.skotniczny.app.user.dto.response;

import java.util.List;

public record GetAllUsersResponseDto(List<UserDto> users) {
}
