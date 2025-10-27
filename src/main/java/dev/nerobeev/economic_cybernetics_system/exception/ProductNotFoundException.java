package dev.nerobeev.economic_cybernetics_system.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //todo <-- правильно ли так делать?
public class ProductNotFoundException extends EntityNotFoundException {
  public ProductNotFoundException(long id) {
    super("Продукт с ID " + id + " не найден, задайте ID сектора");
  }
}
