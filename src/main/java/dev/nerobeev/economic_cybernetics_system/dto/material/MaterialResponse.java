package dev.nerobeev.economic_cybernetics_system.dto.material;

import java.math.BigDecimal;

public record MaterialResponse(
    Long id,
    String name,
    String supplier,
    BigDecimal costPerUnit,
    String code
) {
}
