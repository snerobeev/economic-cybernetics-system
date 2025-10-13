package dev.nerobeev.economic_cybernetics_system.dto.extraction;

public record ExtractionResponse(
    long id,
    int durationDays,          // Длительность добычи в днях
    Long totalCost           // Итоговая себестоимость
) {
}
