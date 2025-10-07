package dev.nerobeev.economic_cybernetics_system.dto.extraction;

import dev.nerobeev.economic_cybernetics_system.entity.ProductionCost;

public record ExtractionCreateRequest(

    String name,
    ProductionCost productionCost
) {
}
