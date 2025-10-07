package dev.nerobeev.economic_cybernetics_system.advice;

import dev.nerobeev.economic_cybernetics_system.dto.error.ErrorMessageResponse;
import dev.nerobeev.economic_cybernetics_system.dto.error.FormatTimeStamp;
import dev.nerobeev.economic_cybernetics_system.exeption.MaterialAlreadyExistsException;
import dev.nerobeev.economic_cybernetics_system.exeption.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements FormatTimeStamp {

  @Override
  public String formatTimeStamp(Instant instant) {
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return localDateTime.format(formatter);
  }

  @ExceptionHandler({ProductNotFoundException.class, MaterialAlreadyExistsException.class})
  public ResponseEntity<ErrorMessageResponse> handleNotFound(RuntimeException exception) {

    log.error("Create operation error: {}", exception.getMessage(), exception);
    var formattedTime = formatTimeStamp(Instant.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body(new ErrorMessageResponse(exception.getMessage(), formattedTime));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorMessageResponse> handleDataIntegrityViolation(DataIntegrityViolationException exception) {

    log.error("Проверьте обязательные поля (NOT NULL): {}", exception.getMessage(), exception);
    var formattedTime = formatTimeStamp(Instant.now());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorMessageResponse(exception.getLocalizedMessage(), formattedTime));

  }

}
