package dev.nerobeev.economic_cybernetics_system.dto.product;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

// Request для создания продукта

public record ProductCreateRequest(
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
    @PastOrPresent
    LocalDate productionDate,
    @NotNull
    Boolean strategic
) {
}
