package dev.nerobeev.economic_cybernetics_system.dto.product;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.entity.Material;

import java.time.LocalDate;
import java.util.List;

public record ProductResponse(
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
        Boolean strategic,
        List<Material> materials
) {
}
