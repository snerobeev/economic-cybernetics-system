package dev.nerobeev.economic_cybernetics_system.exception;

import jakarta.persistence.EntityNotFoundException;

public class ProductionCostNotFoundException extends EntityNotFoundException {
  public ProductionCostNotFoundException(Long id) {
    super("Production cost with ID " + id + " not found.");
  }
}
