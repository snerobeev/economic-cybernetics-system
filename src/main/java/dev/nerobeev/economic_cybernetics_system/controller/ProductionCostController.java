package dev.nerobeev.economic_cybernetics_system.controller;

import dev.nerobeev.economic_cybernetics_system.dto.production.ProductionCostCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.production.ProductionCostResponse;
import dev.nerobeev.economic_cybernetics_system.service.ProductionCostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping
  public ResponseEntity<List<ProductionCostResponse>> getAllProductionCosts() {
    var productionCosts = productionCostService.getAllProductionCosts();
    return ResponseEntity.ok()
                         .header(
                             "X-Total-Count",
                             String.valueOf(productionCosts.size())
                         ).body(productionCosts);
  }
  @GetMapping("/{id}")
  public ProductionCostResponse getProductionCstById(@PathVariable Long id) {
    return productionCostService.getById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProductionCost(@PathVariable Long id) {
    productionCostService.deleteProductionCost(id);
  }

}
