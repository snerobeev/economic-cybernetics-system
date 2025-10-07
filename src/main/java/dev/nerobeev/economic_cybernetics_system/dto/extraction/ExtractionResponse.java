package dev.nerobeev.economic_cybernetics_system.dto.extraction;

import java.math.BigDecimal;

public record ExtractionResponse(
    long id,
    int durationDays,          // Длительность добычи в днях
    BigDecimal totalCost           // Итоговая себестоимость
) {
}
