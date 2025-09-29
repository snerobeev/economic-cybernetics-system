package dev.nerobeev.economic_cybernetics_system.dto.economic_sector;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EconomicSectorCreateRequest(

        @NotBlank(message = "Номер код обязателен")
        @Size(max = 8, message = "Код должен состоять из 2-10 цифр") //todo regexp "^\\d{2,5}$"
        String code,

        @NotBlank(message = "Название сектора обязательно")
        @Size(max = 100, message = "Название должно не превышать 100 символов")
        String name,


        @Size(max = 300, message = "Описание не должно превышать 300 символов")
        String description

) {
}
