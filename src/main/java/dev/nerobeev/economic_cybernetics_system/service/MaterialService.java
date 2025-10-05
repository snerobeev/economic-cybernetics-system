package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.newv.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.newv.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.exeption.MaterialNotFoundException;
import dev.nerobeev.economic_cybernetics_system.mapper.MaterialMapper;
import dev.nerobeev.economic_cybernetics_system.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {
  private final MaterialRepository materialRepository;
  private final MaterialMapper materialMapper;
  private final MarkingGenerator markingGenerator;

  public MaterialResponse createMaterial(MaterialCreateRequest request) {

    var material = materialMapper.toEntity(request, markingGenerator);
    material.setUCode(markingGenerator.generate(MarkingType.MATERIAL));
    var materialName = request.name(); // todo how to equals material?
    var savedMaterial = materialRepository.save(material);

    return materialMapper.toResponse(savedMaterial);
  }

  public List<MaterialResponse> getAllMaterials(){
    return materialRepository.findAll().stream()
        .map(materialMapper::toResponse)
        .toList();
  }

  public MaterialResponse getMaterialByName(String name){
    var material = materialRepository.findMaterialByName(name)
        .orElseThrow(()-> new MaterialNotFoundException(name));
    return materialMapper.toResponse(material);
  }

}
