package dev.nerobeev.economic_cybernetics_system.controller;

import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.repository.MaterialRepository;
import dev.nerobeev.economic_cybernetics_system.repository.ProductionCostRepository;
import dev.nerobeev.economic_cybernetics_system.service.MaterialService;
import dev.nerobeev.economic_cybernetics_system.service.ProducerService;
import dev.nerobeev.economic_cybernetics_system.service.ProductionCostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;
    private final ProductionCostRepository productionCostRepository;
    private final ProductionCostService productionCostService;
    private final ProducerService producerService;
    private final MaterialRepository materialRepository;

    @GetMapping
    public ResponseEntity<List<MaterialResponse>> getAllMaterials() {
        var materials = materialService.getAllMaterials();
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(materials.size())).body(materials);
    }

    @GetMapping("/{id}")
    public MaterialResponse getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialResponse createMaterial(@RequestBody @Valid MaterialCreateRequest request) {
        return materialService.createMaterial(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
    }
}
