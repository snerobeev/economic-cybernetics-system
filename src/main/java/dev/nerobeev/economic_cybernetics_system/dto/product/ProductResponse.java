package dev.nerobeev.economic_cybernetics_system.dto.product;

public record ProductResponse(
    Long id,
    String name,
    String unit,
    Long sectorId,
    String code,
    String description,
    String markingCode
) {
}
