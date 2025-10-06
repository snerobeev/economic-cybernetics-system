package dev.nerobeev.economic_cybernetics_system.advice;

import dev.nerobeev.economic_cybernetics_system.dto.error.ErrorMessageResponse;
import dev.nerobeev.economic_cybernetics_system.exeption.MaterialAlreadyExistsException;
import dev.nerobeev.economic_cybernetics_system.exeption.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ProductNotFoundException.class, MaterialAlreadyExistsException.class})
  public ResponseEntity<ErrorMessageResponse> handleNotFound(RuntimeException exception) {

    log.error("Create product operation error: {}", exception.getMessage(), exception);

    String timestamp = LocalDateTime.now().toString(); //todo <-- правильно ли так делать?
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body(new ErrorMessageResponse(exception.getMessage(), timestamp));
  }

}
