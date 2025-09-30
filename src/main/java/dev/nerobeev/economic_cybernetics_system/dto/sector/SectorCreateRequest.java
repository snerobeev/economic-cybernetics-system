package dev.nerobeev.economic_cybernetics_system.dto.sector;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SectorCreateRequest(

    @NotBlank(message = "Название сектора обязательно")
    @Size(max = 100, message = "Название должно не превышать 100 символов")
    String name,

    @NotBlank(message = "Номер код обязателен")
    @Size(max = 8, message = "Код должен состоять из 2-10 цифр") //todo regexp "^\\d{2,5}$"
    String code,

    @Size(max = 300, message = "Описание не должно превышать 300 символов")
    String description

) {
}
