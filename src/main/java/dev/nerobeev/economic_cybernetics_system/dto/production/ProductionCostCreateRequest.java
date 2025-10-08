package dev.nerobeev.economic_cybernetics_system.dto.production;

import java.math.BigDecimal;

public record ProductionCostCreateRequest(
    String name,
    BigDecimal energyCost,
    BigDecimal laborHours,
    BigDecimal equipmentCost

) {
}
