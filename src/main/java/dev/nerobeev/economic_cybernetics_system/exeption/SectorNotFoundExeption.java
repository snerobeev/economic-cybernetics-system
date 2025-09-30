package dev.nerobeev.economic_cybernetics_system.exeption;

import jakarta.persistence.EntityNotFoundException;

public class SectorNotFoundExeption extends EntityNotFoundException {
  public SectorNotFoundExeption(Long id){
    super("Sector with id " + id + " not found");
  }
}
