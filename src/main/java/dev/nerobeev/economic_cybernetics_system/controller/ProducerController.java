package dev.nerobeev.economic_cybernetics_system.controller;

import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.dto.producer.ProducerResponse;
import dev.nerobeev.economic_cybernetics_system.service.ProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producers")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping
    public ResponseEntity<List<ProducerResponse>> getAllProducers() {
        var producerss = producerService.getAllMaterials();
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(materials.size())).body(materials);
    }

    @GetMapping("/{id}")
    public MaterialResponse getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialResponse createMaterial(@RequestBody @Valid MaterialCreateRequest request) {
        var quantity = request.quantity();
        // todo
        return materialService.createMaterial(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
    }

}
