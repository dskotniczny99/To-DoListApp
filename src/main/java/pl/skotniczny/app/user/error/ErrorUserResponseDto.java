package pl.skotniczny.app.user.error;

import org.springframework.http.HttpStatus;

public record ErrorUserResponseDto(String message, HttpStatus status) {
}
