package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.dto.product.ProductCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.quarry.ProductionCostCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.quarry.ProductionCostResponse;
import dev.nerobeev.economic_cybernetics_system.entity.ProductionCost;
import dev.nerobeev.economic_cybernetics_system.mapper.ProductionCostMapper;
import dev.nerobeev.economic_cybernetics_system.repository.ProductionCostRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class ProductionCostService {

  private final ProductionCostRepository productionCostRepository;
  private final ProductionCostMapper productionCostMapper;

  public ProductionCostResponse createProductionCost(ProductionCostCreateRequest request) {
    var productionCost = productionCostMapper.toEntity(request);
    var savedProductionCost = productionCostRepository.save(productionCost);
    return productionCostMapper.toResponse(savedProductionCost);
  }


}
