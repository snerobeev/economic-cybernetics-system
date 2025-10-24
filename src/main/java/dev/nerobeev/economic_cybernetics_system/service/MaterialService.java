package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialUpdateRequest;
import dev.nerobeev.economic_cybernetics_system.exeption.MaterialNotFoundException;
import dev.nerobeev.economic_cybernetics_system.mapper.MaterialMapper;
import dev.nerobeev.economic_cybernetics_system.repository.MaterialRepository;
import dev.nerobeev.economic_cybernetics_system.repository.ProductionCostRepository;
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
  private final ProductionCostRepository productionCostRepository;
  private final ProductionCostService productionCostService;

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
  public Long calculateCostOfMaterial(String materialName, String prodCostName) { //todo DANYA

    if (materialName == null || prodCostName == null) {
      throw new RuntimeException("MaterialName or productionCostName cannot contains null."); //todo
    }
    if (!materialName.equals(prodCostName)) {
      throw new RuntimeException("MaterialName and productionCostName are not equal."); //todo
    }

    var material = materialRepository.findMaterialByName(materialName)
                                     .orElseThrow(() -> new RuntimeException(
                                         materialName + " not found in materialRepository.")); //todo

    var computedTotalCost = productionCostService.computeTotalCost(prodCostName);
    var sumMaterialWithComputedTotalCost = material.getCostPerUnit() + computedTotalCost;

    material.setCostPerUnit(sumMaterialWithComputedTotalCost); // todo DANYA
    var materialWithCostPerUnit = materialRepository.save(material);

    return materialWithCostPerUnit.getCostPerUnit();
  }

}
