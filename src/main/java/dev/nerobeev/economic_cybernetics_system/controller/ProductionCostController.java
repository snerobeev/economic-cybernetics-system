package dev.nerobeev.economic_cybernetics_system.controller;

import dev.nerobeev.economic_cybernetics_system.dto.quarry.ProductionCostCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.quarry.ProductionCostResponse;
import dev.nerobeev.economic_cybernetics_system.service.ProductionCostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/production-costs")
@RequiredArgsConstructor
public class ProductionCostController {

  private final ProductionCostService productionCostService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductionCostResponse createProductionCost(@Valid @RequestBody ProductionCostCreateRequest request) {
    return productionCostService.createProductionCost(request);
  }
}
