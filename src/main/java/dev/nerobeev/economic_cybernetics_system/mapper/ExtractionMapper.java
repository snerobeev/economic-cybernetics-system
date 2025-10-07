package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.dto.extraction.ExtractionCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.extraction.ExtractionResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Enterprise;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {JsonNullableMapper.class})

public interface ExtractionMapper {

  Enterprise toEntity(ExtractionCreateRequest request);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "durationDays,", target = "durationDays")
  @Mapping(target = "totalCost", expression = "java")
  ExtractionResponse toResponse(Enterprise enterprise);
}
