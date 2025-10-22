package dev.nerobeev.economic_cybernetics_system.dto.material;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MaterialCreateRequest(
    @NotNull
    String name,
    @NotNull
    UnitOfMeasure unit,
    /*
     * Себестоимость единицы.
     * ЭТО ПОЛЕ ОПЦИОНАЛЬНО и НЕ ДОЛЖНО заполняться клиентом,
     * так как рассчитывается автоматически сервисом ProductionCostService.
     * Передача null или отсутствие поля приветствуется.
     */
    Long costPerUnit,
    @NotNull
    Long pricePerUnit,
    @NotNull
    String producer,
    @NotNull
    Long quantity,
    @NotNull
    Status status,
    @NotNull
    IndustryCode industryCode,
    @NotNull
    String planPeriod,
    @NotNull
    LocalDate productionDate,
    @NotNull
    Boolean strategic

) {
}
