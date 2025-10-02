package dev.nerobeev.economic_cybernetics_system.exeption;

public class MaterialAlreadyExistsException extends RuntimeException {
  public MaterialAlreadyExistsException(String name) {

    super("Материал с именем " + name + " уже существует");
  }
}