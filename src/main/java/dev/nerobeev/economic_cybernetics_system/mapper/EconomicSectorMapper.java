package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.EconomicSectorCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.EconomicSectorResponse;
import dev.nerobeev.economic_cybernetics_system.entity.EconomicSector;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {JsonNullableMapper.class})
public interface EconomicSectorMapper {

  // Request DTO -> Entity (Запрос на создание нового сектора)
  EconomicSector toEntity(EconomicSectorCreateRequest dto);

  // Entity -> Response DTO (Ответ клиенту)
  EconomicSectorResponse toDto(EconomicSector sector);

  // List<Entity> -> List<Response DTO>
  List<EconomicSectorResponse> toDto(List<EconomicSector> sectors);


  // Обновление существующей сущности из DTO (для PUT/PATCH)
  void updateEntityFromDto(EconomicSectorCreateRequest dto, @MappingTarget EconomicSector sector);
}
