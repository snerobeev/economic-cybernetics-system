package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {JsonNullableMapper.class})

public interface MaterialMapper {
  // Request DTO -> Entity (Запрос на создание нового материала)
  @Mapping(target = "id", ignore = true)
  Material toEntity(MaterialCreateRequest request, @Context MarkingGenerator generator);

  MaterialResponse toResponse(Material material);

  @AfterMapping
  default void assignCode(@MappingTarget Material material, @Context MarkingGenerator generator) {
    if(material.getCode() == null) {
      material.setCode(generator.generate(MarkingType.MATERIAL));
    }
  }
}
