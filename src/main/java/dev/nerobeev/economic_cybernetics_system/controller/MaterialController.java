package dev.nerobeev.economic_cybernetics_system.controller;

import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.service.MaterialService;
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

  @GetMapping
  public ResponseEntity<List<MaterialResponse>> getAllMaterials(){
    var materials = materialService.getAllMaterials();
    return ResponseEntity.ok().header("X-Total-Count", String.valueOf(materials.size())).body(materials);
  }

  @GetMapping("/{name}")
  public MaterialResponse getMaterialByName(@PathVariable String name) {
    return materialService.getMaterialByName(name);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MaterialResponse createMaterial(@RequestBody @Valid MaterialCreateRequest request) {
    return materialService.createMaterial(request);
  }
}
