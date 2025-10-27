package dev.nerobeev.economic_cybernetics_system.exception;

public class CompanyNotFoundException extends RuntimeException {
  public CompanyNotFoundException(Long id) {
    super("Producer with ID " + id + " not found");
  }
}
