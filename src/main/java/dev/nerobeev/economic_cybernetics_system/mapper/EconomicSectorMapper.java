package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.EconomicSectorRequestDto;
import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.EconomicSectorResponseDto;
import dev.nerobeev.economic_cybernetics_system.entity.EconomicSector;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {JsonNullableMapper.class})
public interface EconomicSectorMapper {

  // Entity -> Response DTO (Для отправки клиенту)
  EconomicSectorResponseDto toDto(EconomicSector sector);

  // List<Entity> -> List<Response DTO>
  List<EconomicSectorResponseDto> toDto(List<EconomicSector> sectors);

  // Request DTO -> Entity (Для создания нового сектора)
  EconomicSector toEntity(EconomicSectorRequestDto dto);

  // Обновление существующей сущности из DTO (для PUT/PATCH)
  void updateEntityFromDto(EconomicSectorRequestDto dto, @MappingTarget EconomicSector sector);
}
