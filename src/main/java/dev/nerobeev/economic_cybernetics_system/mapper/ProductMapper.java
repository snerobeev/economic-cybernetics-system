package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
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
  @Mapping(target = "id", ignore = true)
  Product toEntity(ProductCreateRequest request,@Context MarkingGenerator generator);

  // Entity -> Response DTO (Ответ клиенту)
  @Mapping(source = "sector.id", target = "sectorId")
  @Mapping(source = "sector.code", target = "code")
  @Mapping(source = "sector.name", target = "description")
  ProductResponse toResponse(Product product);

  @AfterMapping
  default void assignCode(@MappingTarget Product product, @Context MarkingGenerator generator) {
    if (product.getCode() == null) {
      product.setCode(generator.generate(MarkingType.PRODUCT));
    }
  }
}