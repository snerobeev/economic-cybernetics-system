package dev.nerobeev.economic_cybernetics_system.exeption;

import jakarta.persistence.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {
  public ProductNotFoundException(long id) {
    super("Продукт с ID " + id + " не найден, задайте ID сектора");
  }
}
