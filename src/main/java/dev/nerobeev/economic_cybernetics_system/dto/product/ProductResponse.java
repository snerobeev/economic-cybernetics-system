package dev.nerobeev.economic_cybernetics_system.dto.product;

import dev.nerobeev.economic_cybernetics_system.domain.newv.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.newv.Status;
import dev.nerobeev.economic_cybernetics_system.domain.newv.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Material;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProductResponse(
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
        Boolean strategic,
        List<Material> materials
) {
}
