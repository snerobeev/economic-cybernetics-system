package dev.nerobeev.economic_cybernetics_system.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Request для создания продукта

public record ProductCreateRequest(

    @NotBlank(message = "Название продукта обязательно")
    @Size(max = 100, message = "Название должно не превышать 100 символов")
    String name,

    @NotBlank(message = "Единица измерения обязательна")
    @Size(max = 50, message = "Единица измерения не должна превышать 50 символов")
    String unit,

    @NotNull(message = "Код сектора обязателен")
    Long sector_id,

    String markingCode) {
}
