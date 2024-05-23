package pl.skotniczny.app.task.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.skotniczny.app.task.model.TaskNotFoundException;
import pl.skotniczny.app.user.model.UserNotFoundException;

@RestControllerAdvice(assignableTypes = TaskNotFoundException.class)
@Log4j2
public class TaskErrorHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorTaskResponseDto> handleException(UserNotFoundException exception) {
        log.warn("TaskNotFoundException while accessing the task");
        ErrorTaskResponseDto errorUserResponseDto = new ErrorTaskResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorUserResponseDto);
    }
}
