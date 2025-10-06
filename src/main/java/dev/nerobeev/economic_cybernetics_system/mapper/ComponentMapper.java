package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Component;
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
