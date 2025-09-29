package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.dto.product.ProductCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.product.ProductResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Product;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {JsonNullableMapper.class})

public interface ProductMapper {

  // Request DTO -> Entity (Запрос на создание нового продукта)
  @Mapping(target = "id",ignore = true)
  @Mapping(target = "name", ignore = true)
  @Mapping(target = "unit", ignore = true)
  @Mapping(target = "sector", ignore = true)
  Product toEntity(ProductCreateRequest dto);

  // Entity -> Response DTO (Ответ клиенту)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", ignore = true)
  @Mapping(target = "unit", ignore = true)
  ProductResponse toResponse(Product product);
}
