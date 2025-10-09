package dev.nerobeev.economic_cybernetics_system.exeption;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductionCostNotFoundException extends EntityNotFoundException {
  public ProductionCostNotFoundException(Long id) {
    super("Производственные издержки с ID " + id + " в базе не найдены.");
  }
}
