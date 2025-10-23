package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialUpdateRequest;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {JsonNullableMapper.class})

public interface MaterialMapper {

  Material toEntity(MaterialCreateRequest request, @Context MarkingGenerator generator);

  @Mapping(source = "code", target = "code")
  MaterialResponse toResponse(Material entity);

  @Mapping(source = "code", target = "code")
  void updateEntity(@MappingTarget Material entity, MaterialUpdateRequest dto);

}

