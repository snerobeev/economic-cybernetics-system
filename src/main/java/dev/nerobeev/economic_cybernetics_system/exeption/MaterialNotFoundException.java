package dev.nerobeev.economic_cybernetics_system.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //todo <-- правильно ли так делать?
public class MaterialNotFoundException extends RuntimeException {
  public MaterialNotFoundException(String name) {

    super("Материал с именем " + name + " не найден");
  }
}
