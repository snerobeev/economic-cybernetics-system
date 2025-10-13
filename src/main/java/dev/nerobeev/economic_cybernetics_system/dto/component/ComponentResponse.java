package dev.nerobeev.economic_cybernetics_system.dto.component;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.dto.product.ProductResponse;

import java.time.LocalDate;
import java.util.Set;

public record ComponentResponse(
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
        Set<MaterialResponse> materials,
        Set<ProductResponse> products
) {
}
