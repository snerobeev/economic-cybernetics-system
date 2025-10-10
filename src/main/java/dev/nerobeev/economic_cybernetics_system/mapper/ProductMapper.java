package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.dto.product.ProductCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.product.ProductResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Product;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {MaterialMapper.class,JsonNullableMapper.class})

public interface ProductMapper {

  // Request DTO -> Entity (Запрос на создание нового продукта)
  @Mapping(target = "id", ignore = true)
  Product toEntity(ProductCreateRequest request,@Context MarkingGenerator generator);

  // Entity -> Response DTO (Ответ клиенту)
  ProductResponse toResponse(Product product);

}