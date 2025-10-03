package dev.nerobeev.economic_cybernetics_system.dto.material;

import dev.nerobeev.economic_cybernetics_system.domain.measure.UnitOfMeasure;

import java.math.BigDecimal;

public record MaterialCreateRequest(
    String name,
    String supplier,
    BigDecimal quantity,
    BigDecimal costPerUnit,
    UnitOfMeasure unit
) {
}
