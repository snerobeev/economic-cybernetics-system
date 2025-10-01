package dev.nerobeev.economic_cybernetics_system.exeption;

import jakarta.persistence.EntityNotFoundException;

public class ProductNotFoundExeption extends EntityNotFoundException {
  public ProductNotFoundExeption(long id) {
    super("Продукт с ID " + id + " не найден");
  }
}
