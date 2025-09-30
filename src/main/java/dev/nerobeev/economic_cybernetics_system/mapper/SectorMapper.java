package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.SectorCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.SectorResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Sector;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {JsonNullableMapper.class})
public interface SectorMapper {

  // Request DTO -> Entity (Запрос на создание нового сектора)
  @Mapping(target = "id",ignore = true)
  Sector toEntity(SectorCreateRequest dto);

  // Entity -> Response DTO (Ответ клиенту)
  SectorResponse toResponse(Sector sector);

  // List<Entity> -> List<Response DTO>
  List<SectorResponse> toDto(List<Sector> sectors);


  // Обновление существующей сущности из DTO (для PUT/PATCH)
  void updateEntityFromDto(SectorCreateRequest dto, @MappingTarget Sector sector);
}
