package pl.skotniczny.app.task.error;

import org.springframework.http.HttpStatus;

public record ErrorTaskResponseDto(String message, HttpStatus status) {
}
