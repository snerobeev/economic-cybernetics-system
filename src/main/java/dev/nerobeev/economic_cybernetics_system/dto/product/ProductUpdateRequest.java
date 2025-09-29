package dev.nerobeev.economic_cybernetics_system.dto.product;

import jakarta.validation.constraints.NotBlank;
import org.openapitools.jackson.nullable.JsonNullable;

public record ProductUpdateRequest(
    @NotBlank
    JsonNullable<String> name,
    JsonNullable<String> unit,
    JsonNullable<String> sectorId


) {
}
