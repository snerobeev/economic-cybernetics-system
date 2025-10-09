package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.exeption.MaterialNotFoundException;
import dev.nerobeev.economic_cybernetics_system.mapper.MaterialMapper;
import dev.nerobeev.economic_cybernetics_system.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialService {
  private final MaterialRepository materialRepository;
  private final MaterialMapper materialMapper;
  private final MarkingGenerator markingGenerator;

  public MaterialResponse createMaterial(MaterialCreateRequest request) {

    var material = materialMapper.toEntity(request, markingGenerator);
    material.setUCode(markingGenerator.generate(MarkingType.MATERIAL));
    var savedMaterial = materialRepository.save(material);
    log.info("Material with ID: {}", material.getId() + " created");
    return materialMapper.toResponse(savedMaterial);
  }

  public List<MaterialResponse> getAllMaterials() {
    return materialRepository.findAll().stream()
                             .map(materialMapper::toResponse)
                             .toList();
  }

  public MaterialResponse getMaterialById(Long id) {
    var material = materialRepository.findMaterialById(id)
                                     .orElseThrow(() -> new MaterialNotFoundException(id));
    return materialMapper.toResponse(material);
  }

  public void deleteMaterial(Long id) {
    var material = materialRepository.findMaterialById(id)
        .orElseThrow(()-> new MaterialNotFoundException(id));
    materialRepository.delete(material);
    log.info("Material with id: {}", material.getId() + " deleted");
  }

}
