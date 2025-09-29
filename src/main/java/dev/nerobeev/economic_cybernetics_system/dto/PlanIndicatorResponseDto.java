package dev.nerobeev.economic_cybernetics_system.dto;

import dev.nerobeev.economic_cybernetics_system.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlanIndicatorResponseDto(
    Long id,
    Product product,
    Integer planYear,
    Integer planQuarter,
    BigDecimal plannedOutput,
    BigDecimal actualOutput,
    LocalDateTime createAt,

    BigDecimal getDeviationPercent, // Отклонение в процентах
    Boolean isCompleted         // Статус выполнения плана
) {

  }
