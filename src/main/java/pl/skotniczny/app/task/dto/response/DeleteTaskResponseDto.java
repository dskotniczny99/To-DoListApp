package pl.skotniczny.app.task.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteTaskResponseDto(String message, HttpStatus httpStatus) {
}
