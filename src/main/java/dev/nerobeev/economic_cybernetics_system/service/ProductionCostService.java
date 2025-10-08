package dev.nerobeev.economic_cybernetics_system.service;

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
        return productionCostMapper.toResponse(savedProductionCost);
    }

    public ProductionCostResponse getTotalCost(ProductionCostCreateRequest request) {
        var energy = productionCostRepository.findByEnergyCost(request.energyCost());

       return null;
    }
}
