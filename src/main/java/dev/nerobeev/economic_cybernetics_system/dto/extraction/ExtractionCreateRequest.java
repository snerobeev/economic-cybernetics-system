package dev.nerobeev.economic_cybernetics_system.dto.extraction;

import dev.nerobeev.economic_cybernetics_system.entity.Enterprise;

public record ExtractionCreateRequest(

    String name,
    Enterprise enterprise
) {
}
