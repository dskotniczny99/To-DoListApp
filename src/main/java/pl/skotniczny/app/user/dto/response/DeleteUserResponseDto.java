package pl.skotniczny.app.user.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteUserResponseDto(String message, HttpStatus status) {
}
