package dev.nerobeev.economic_cybernetics_system.exception;

public class ComponentNotFoundException extends RuntimeException {
  public ComponentNotFoundException(Long id) {

    super("Component with ID " + id + " not found");
  }
}
