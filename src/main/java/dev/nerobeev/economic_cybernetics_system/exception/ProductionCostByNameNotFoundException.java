package dev.nerobeev.economic_cybernetics_system.exception;

public class ProductionCostByNameNotFoundException extends RuntimeException {
  public ProductionCostByNameNotFoundException(String message) {

    super("Production cost with this name " + message + " not found.");
  }
}
