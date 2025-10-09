package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.dto.production.ProductionCostCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.production.ProductionCostResponse;
import dev.nerobeev.economic_cybernetics_system.exeption.ProductionCostNotFoundException;
import dev.nerobeev.economic_cybernetics_system.mapper.ProductionCostMapper;
import dev.nerobeev.economic_cybernetics_system.repository.ProductionCostRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ProductionCostService {

  private final ProductionCostMapper productionCostMapper;
  private final ProductionCostRepository productionCostRepository;

  public ProductionCostResponse createProductionCost(ProductionCostCreateRequest request) {
    var productionCost = productionCostMapper.toEntity(request);
    var savedProductionCost = productionCostRepository.save(productionCost);
    log.info("ProductionCost with ID: {}", savedProductionCost.getId() + " created");
    return productionCostMapper.toResponse(savedProductionCost);
  }

  public void deleteProductionCost(Long id) {
    var productionCost = productionCostRepository.findById(id)
                                                 .orElseThrow(() -> new ProductionCostNotFoundException(id));
    productionCostRepository.delete(productionCost);
    log.info("ProductionCost with id: {}", productionCost.getId() + " deleted");
  }

  public List<ProductionCostResponse> getAllProductionCosts() {
    log.info("All ProductionCost found");
    return productionCostRepository.findAll().stream()
                                   .map(productionCostMapper::toResponse)
                                   .toList();
  }

  public ProductionCostResponse getById(Long id) {
    var productionCost = productionCostRepository.findById(id)
                                                 .orElseThrow(() -> new ProductionCostNotFoundException(id));
    return productionCostMapper.toResponse(productionCost);
  }

  public ProductionCostResponse getTotalCost(ProductionCostCreateRequest request) {
    var energyCost = request.energyCost();
    var cost = productionCostRepository.findByEnergyCost(energyCost);

    return null; //todo
  }
}
