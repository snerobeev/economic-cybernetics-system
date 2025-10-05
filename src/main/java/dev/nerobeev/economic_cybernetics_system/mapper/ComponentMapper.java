package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.domain.newv.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentResponse;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.entity.Component;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MaterialMapper.class, ProductMapper.class})
public interface ComponentMapper {

    // Request DTO -> Entity (Запрос на создание нового материала)
    Component toEntity(ComponentCreateRequest request, @Context MarkingGenerator generator);

    @Mapping(source = "materials", target = "materials")
    @Mapping(source = "products", target = "products")
    ComponentResponse toResponse(Component component);

}
