package dev.nerobeev.economic_cybernetics_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MaterialNotFoundException extends RuntimeException {
  public MaterialNotFoundException(Long id) {

    super("Material with ID " + id + " not found");
  }
}
