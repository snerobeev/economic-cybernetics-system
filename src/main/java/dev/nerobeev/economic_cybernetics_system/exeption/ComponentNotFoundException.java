package dev.nerobeev.economic_cybernetics_system.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //todo <-- правильно ли так делать?
public class ComponentNotFoundException extends RuntimeException {
  public ComponentNotFoundException(Long id) {

    super("Component with ID " + id + " not found");
  }
}
