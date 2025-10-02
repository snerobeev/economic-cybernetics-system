package dev.nerobeev.economic_cybernetics_system.dto.material;

import java.math.BigDecimal;

public record MaterialCreateRequest(
    String name,
    String supplier,
    BigDecimal cost
) {
}
