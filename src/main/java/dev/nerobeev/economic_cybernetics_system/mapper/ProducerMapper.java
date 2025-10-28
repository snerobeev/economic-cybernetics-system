package dev.nerobeev.economic_cybernetics_system.mapper;

import dev.nerobeev.economic_cybernetics_system.dto.producer.ProducerCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.producer.ProducerResponse;
import dev.nerobeev.economic_cybernetics_system.dto.producer.ProducerUpdateRequest;
import dev.nerobeev.economic_cybernetics_system.entity.Producer;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {JsonNullableMapper.class})

public interface ProducerMapper {

    Producer toEntity(ProducerCreateRequest request);
    ProducerResponse toResponse(Producer entity);

}
