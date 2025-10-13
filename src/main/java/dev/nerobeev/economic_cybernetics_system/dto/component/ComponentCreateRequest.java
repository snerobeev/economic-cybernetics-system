package dev.nerobeev.economic_cybernetics_system.dto.component;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import dev.nerobeev.economic_cybernetics_system.entity.Product;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public record ComponentCreateRequest(
        @NotNull
        String name,
        @NotNull
        UnitOfMeasure unit,
        @NotNull
        Long costPerUnit,
        @NotNull
        Long pricePerUnit,
        @NotNull
        String producer,
        @NotNull
        Long quantity,
        @NotNull
        Status status,
        @NotNull
        IndustryCode industryCode,
        @NotNull
        String planPeriod,
        @NotNull
        LocalDate productionDate,
        @NotNull
        Boolean strategic,
        Set<Product> products,
        Set<Material> materials
) {
}
