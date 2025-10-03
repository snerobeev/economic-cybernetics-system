package dev.nerobeev.economic_cybernetics_system.dto.material;

import dev.nerobeev.economic_cybernetics_system.domain.newv.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.newv.Status;
import dev.nerobeev.economic_cybernetics_system.domain.newv.measure.UnitOfMeasure;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaterialCreateRequest(
    String name,
//    String uCode,
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
