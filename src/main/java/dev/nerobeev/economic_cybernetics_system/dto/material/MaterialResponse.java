package dev.nerobeev.economic_cybernetics_system.dto.material;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;

import java.time.LocalDate;

public record MaterialResponse(
    Long id,
    String name,
    String uCode,
    UnitOfMeasure unit,
    Long costPerUnit,
    Long pricePerUnit,
    String producer,
    Long quantity,
    Status status,
    IndustryCode industryCode,
    String planPeriod,
    LocalDate productionDate,
    Boolean strategic
) {
}
