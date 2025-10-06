package dev.nerobeev.economic_cybernetics_system.dto.material;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaterialCreateRequest(
    String name,
    UnitOfMeasure unit,
    BigDecimal costPerUnit,
    BigDecimal pricePerUnit,
    String producer,
    BigDecimal quantity,
    Status status,
    IndustryCode industryCode,
    String planPeriod,
    LocalDate productionDate,
    Boolean strategic

) {
}
