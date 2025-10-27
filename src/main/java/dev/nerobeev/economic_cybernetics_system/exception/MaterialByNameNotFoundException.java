package dev.nerobeev.economic_cybernetics_system.exception;

public class MaterialByNameNotFoundException extends RuntimeException {
  public MaterialByNameNotFoundException(String name) {

    super("Material with name " + name + " not found");
  }
}
