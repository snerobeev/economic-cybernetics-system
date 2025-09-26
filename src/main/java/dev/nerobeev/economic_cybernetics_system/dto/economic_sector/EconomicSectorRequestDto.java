package dev.nerobeev.economic_cybernetics_system.dto.economic_sector;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EconomicSectorRequestDto(
    @NotBlank(message = "Sector name is required")
    @Size(max = 100)
    String name,

    @NotBlank(message = "Sector code is required")
    @Pattern(regexp = "^[0-9]{2,5}$", message = "Code must be 2-5 digits")
    String code,

    @Size(max = 255)
    String description

) {
}
