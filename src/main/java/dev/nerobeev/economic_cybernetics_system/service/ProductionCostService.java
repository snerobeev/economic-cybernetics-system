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
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional(readOnly = true)
  public void deleteProductionCost(Long id) {
    var productionCost = productionCostRepository.findById(id)
                                                 .orElseThrow(() -> new ProductionCostNotFoundException(id));
    productionCostRepository.delete(productionCost);
    log.info("ProductionCost with id: {}", productionCost.getId() + " deleted");
  }

  @Transactional(readOnly = true)
  public List<ProductionCostResponse> getAllProductionCosts() {
    log.info("All ProductionCost found");
    return productionCostRepository.findAll().stream()
                                   .map(productionCostMapper::toResponse)
                                   .toList();
  }

  @Transactional(readOnly = true)
  public ProductionCostResponse getById(Long id) {
    var productionCost = productionCostRepository.findById(id)
                                                 .orElseThrow(() -> new ProductionCostNotFoundException(id));
    return productionCostMapper.toResponse(productionCost);
  }

  public Long getTotalCost() {
    var result = productionCostRepository.findAll().stream()
                                         .mapToLong(v ->
                                                            v.getEnergyCost() +
                                                            v.getLaborHours() +
                                                            v.getEquipmentCost() +
                                                            v.getMaterialCost() +
                                                            v.getLogisticsCost() +
                                                            v.getLicenseCost() +
                                                            v.getTaxCost() +
                                                            v.getSocialCost() +
                                                            v.getAmortizationCost() +
                                                            v.getEquipmentMaintenanceCost() +
                                                            v.getAdministrativeCost() +
                                                            v.getRentalCost() +
                                                            v.getCommunicationCost() +
                                                            v.getInsuranceCost() +
                                                            v.getResearchAndDevelopmentCost() +
                                                            v.getInterestCost() +
                                                            v.getEcoCost());

    return result.sum();
  }
}
