package dev.nerobeev.economic_cybernetics_system.exeption;

public class ComponentNotFoundException extends RuntimeException {
  public ComponentNotFoundException(Long id) {

    super("Component with ID " + id + " not found");
  }
}
