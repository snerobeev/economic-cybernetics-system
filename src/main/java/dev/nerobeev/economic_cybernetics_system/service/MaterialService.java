package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialUpdateRequest;
import dev.nerobeev.economic_cybernetics_system.exception.MaterialNotFoundException;
import dev.nerobeev.economic_cybernetics_system.mapper.MaterialMapper;
import dev.nerobeev.economic_cybernetics_system.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    material.setCode(markingGenerator.generate(MarkingType.MATERIAL));
    var savedMaterial = materialRepository.save(material);
    log.info("Material with ID: {}", material.getId() + " created");
    return materialMapper.toResponse(savedMaterial);
  }

  @Transactional(readOnly = true)
  public List<MaterialResponse> getAllMaterials() {
    return materialRepository.findAll().stream()
                             .map(materialMapper::toResponse)
                             .toList();
  }

  @Transactional(readOnly = true)
  public MaterialResponse getMaterialById(Long id) {
    var material = materialRepository.findMaterialById(id)
                                     .orElseThrow(() -> new MaterialNotFoundException(id));
    return materialMapper.toResponse(material);
  }

  public MaterialResponse updateMaterial(Long id, MaterialUpdateRequest updateRequest) {
    var material = materialRepository.findMaterialById(id)
                                     .orElseThrow(() -> new MaterialNotFoundException(id));
    materialMapper.updateEntity(material, updateRequest);
    var updateMaterial = materialRepository.save(material);
    return materialMapper.toResponse(updateMaterial);
  }

  public void deleteMaterial(Long id) {
    var material = materialRepository.findMaterialById(id)
                                     .orElseThrow(() -> new MaterialNotFoundException(id));
    materialRepository.delete(material);
    log.info("Material with id: {}", material.getId() + " deleted.");
  }

  // расчет стоимости Материала
  public Long calculateCostPerUnit(String materialName) {

    if (materialName == null) {
      throw new RuntimeException("MaterialName is null."); //todo
    }

    var material = materialRepository.findMaterialByName(materialName)
                                     .orElseThrow();

    var materialWithCostPerUnit = materialRepository.save(material);

    return materialWithCostPerUnit.getCostPerUnit();
  }

}
