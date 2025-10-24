package dev.nerobeev.economic_cybernetics_system.dto.production;


public record ProductionCostResponse(
    Long id,
    String name,
    Long energyCost,
    Long laborHours,
    Long equipmentCost,
    Long materialCost,
    Long logisticsCost,
    Long licenseCost,
    Long taxCost,
    Long socialCost,
    Long amortizationCost,
    Long equipmentMaintenanceCost,
    Long administrativeCost,
    Long rentalCost,
    Long communicationCost,
    Long insuranceCost,
    Long researchAndDevelopmentCost,
    Long interestCost,
    Long ecoCost,
    Long total //todo
) {
}
