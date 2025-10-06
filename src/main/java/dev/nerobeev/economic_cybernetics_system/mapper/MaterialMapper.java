package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
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
    Material toEntity(MaterialCreateRequest request, @Context MarkingGenerator generator);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "uCode", target = "uCode")
    @Mapping(source = "unit", target = "unit")
    @Mapping(source = "costPerUnit", target = "costPerUnit")
    @Mapping(source = "pricePerUnit", target = "pricePerUnit")
    @Mapping(source = "producer", target = "producer")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "industryCode", target = "industryCode")
    @Mapping(source = "planPeriod", target = "planPeriod")
    @Mapping(source = "productionDate", target = "productionDate")
    @Mapping(source = "strategic", target = "strategic")
    MaterialResponse toResponse(Material material);

}

