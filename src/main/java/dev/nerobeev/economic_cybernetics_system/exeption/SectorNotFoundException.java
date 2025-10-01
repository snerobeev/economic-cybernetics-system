package dev.nerobeev.economic_cybernetics_system.exeption;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //todo <-- правильно ли так делать?
public class SectorNotFoundException extends EntityNotFoundException {
    public SectorNotFoundException(Long id) {
        super("Сектор с кодом " + id + " не найден");
    }
}
