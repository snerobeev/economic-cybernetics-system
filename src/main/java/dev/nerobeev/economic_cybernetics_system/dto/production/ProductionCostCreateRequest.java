package dev.nerobeev.economic_cybernetics_system.dto.production;

import jakarta.validation.constraints.NotNull;

public record ProductionCostCreateRequest(
    @NotNull
    String name,
    @NotNull
    Long energyCost,
    @NotNull
    Long laborHours,
    @NotNull
    Long equipmentCost,
    @NotNull
    Long materialCost,
    @NotNull
    Long logisticsCost,
    @NotNull
    Long licenseCost,
    @NotNull
    Long taxCost,
    @NotNull
    Long socialCost,
    @NotNull
    Long amortizationCost,
    @NotNull
    Long equipmentMaintenanceCost,
    @NotNull
    Long administrativeCost,
    @NotNull
    Long rentalCost,
    @NotNull
    Long communicationCost,
    @NotNull
    Long insuranceCost,
    @NotNull
    Long researchAndDevelopmentCost,
    @NotNull
    Long interestCost,
    @NotNull
    Long ecoCost
) {
}
