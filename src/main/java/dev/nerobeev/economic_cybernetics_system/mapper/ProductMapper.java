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
  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
//  @Mapping(source = "uCode", target = "uCode")
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
  @Mapping(source = "materials", target = "materials")
  ProductResponse toResponse(Product product);

}