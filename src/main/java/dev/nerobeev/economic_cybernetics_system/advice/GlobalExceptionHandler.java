package dev.nerobeev.economic_cybernetics_system.advice;

import dev.nerobeev.economic_cybernetics_system.dto.error.ErrorMessageResponse;
import dev.nerobeev.economic_cybernetics_system.dto.error.FormatTimeStamp;
import dev.nerobeev.economic_cybernetics_system.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
  public String formatTimeStamp(Instant instant) { // todo Jakson config
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return localDateTime.format(formatter);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({ProductNotFoundException.class, MaterialAlreadyExistsException.class})
  public ErrorMessageResponse handleProductNotFound(RuntimeException exception) {
    log.error("Create Product operation error: {}", exception.getMessage(), exception);
    var formattedTime = formatTimeStamp(Instant.now());
    return new ErrorMessageResponse(exception.getMessage(), formattedTime);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorMessageResponse> handleDataIntegrityViolation(DataIntegrityViolationException exception) {

    log.error("Check fields (NOT NULL): {}", exception.getMessage(), exception);
    var formattedTime = formatTimeStamp(Instant.now());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorMessageResponse(exception.getLocalizedMessage(), formattedTime));
  }

  @ExceptionHandler({ProductionCostNotFoundException.class})
  public ResponseEntity<ErrorMessageResponse> handleProductionCostNotFound(RuntimeException exception) {
    log.warn("ProductCost found warn : {} ", exception.getMessage(), exception);
    var formattedTime = formatTimeStamp(Instant.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body(new ErrorMessageResponse(exception.getMessage(), formattedTime));
  }

  @ExceptionHandler({ProductionCostByNameNotFoundException.class})
  public ResponseEntity<ErrorMessageResponse> handleProductionCostByNameNotFound(RuntimeException exception) {
    log.warn("ProductCost by name found warn : {} ", exception.getMessage(), exception);
    var formattedTime = formatTimeStamp(Instant.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body(new ErrorMessageResponse(exception.getMessage(), formattedTime));
  }

  @ExceptionHandler({ComponentNotFoundException.class})
  public ResponseEntity<ErrorMessageResponse> handleComponentNotFound(RuntimeException exception) {
    log.warn("Component found warn : {} ", exception.getMessage(), exception);
    var formattedTime = formatTimeStamp(Instant.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body(new ErrorMessageResponse(exception.getMessage(), formattedTime));
  }

  @ExceptionHandler({MaterialNotFoundException.class})
  public ResponseEntity<ErrorMessageResponse> handleMaterialNotFound(RuntimeException exception) {
    log.warn("Material found warn : {} ", exception.getMessage(), exception);
    var formattedTime = formatTimeStamp(Instant.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body(new ErrorMessageResponse(exception.getMessage(), formattedTime));
  }

  @ExceptionHandler({MaterialByNameNotFoundException.class})
  public ResponseEntity<ErrorMessageResponse> handleMaterialWithThisNameNotFound(RuntimeException exception) {
    log.warn("Material name found warn : {} ", exception.getMessage(), exception);
    var formattedTime = formatTimeStamp(Instant.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body(new ErrorMessageResponse(exception.getMessage(), formattedTime));
  }

  @ExceptionHandler({CompanyNotFoundException.class})
  public ResponseEntity<ErrorMessageResponse> handleCompanyNotFound(RuntimeException exception) {
    log.warn("Producer found warn : {} ", exception.getMessage(), exception);
    var formattedTime = formatTimeStamp(Instant.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body(new ErrorMessageResponse(exception.getMessage(), formattedTime));
  }



}
