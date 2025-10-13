package dev.nerobeev.economic_cybernetics_system.dto.production;

public record ProductionCostCreateRequest(
    String name,
    Long energyCost,
    Long laborHours,
    Long equipmentCost

) {
}
