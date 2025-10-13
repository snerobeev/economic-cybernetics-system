package dev.nerobeev.economic_cybernetics_system.dto.production;


public record ProductionCostResponse(
    Long id,
    String name,
    Long energyCost,
    Long laborHours,
    Long equipmentCost

) {
}
