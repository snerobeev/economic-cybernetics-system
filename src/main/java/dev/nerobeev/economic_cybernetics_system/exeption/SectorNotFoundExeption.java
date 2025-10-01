package dev.nerobeev.economic_cybernetics_system.exeption;

import jakarta.persistence.EntityNotFoundException;

public class SectorNotFoundExeption extends EntityNotFoundException {
    public SectorNotFoundExeption(Long id) {
        super("Сектор с кодом " + id + " не найден");
    }
}
