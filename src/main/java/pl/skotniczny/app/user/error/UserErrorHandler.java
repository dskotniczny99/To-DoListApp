package pl.skotniczny.app.user.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.skotniczny.app.user.controller.UserRestController;
import pl.skotniczny.app.user.model.UserNotFoundException;

@RestControllerAdvice(assignableTypes = UserRestController.class)
@Log4j2
public class UserErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorUserResponseDto> handleException(UserNotFoundException exception) {
        log.warn("UserNotFoundException while accessing user");
        ErrorUserResponseDto errorUserResponseDto = new ErrorUserResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorUserResponseDto);
    }

}
