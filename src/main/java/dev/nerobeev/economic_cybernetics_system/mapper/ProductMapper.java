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

  @Mapping(target = "id", ignore = true)
  Product toEntity(ProductCreateRequest request,@Context MarkingGenerator generator);

  ProductResponse toResponse(Product entity);

}