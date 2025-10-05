package dev.nerobeev.economic_cybernetics_system.dto.component;

import dev.nerobeev.economic_cybernetics_system.domain.newv.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.newv.Status;
import dev.nerobeev.economic_cybernetics_system.domain.newv.UnitOfMeasure;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ComponentCreateRequest(
        Long id,
        String name,
        String uCode,
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
