package dev.nerobeev.economic_cybernetics_system.dto.production;

import java.math.BigDecimal;

public record ProductionCostResponse(
    Long id,
    String name,
    BigDecimal energyCost,
    BigDecimal laborHours,
    BigDecimal equipmentCost

) {
}
