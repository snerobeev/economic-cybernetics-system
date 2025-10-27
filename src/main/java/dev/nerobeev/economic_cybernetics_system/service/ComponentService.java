package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentResponse;
import dev.nerobeev.economic_cybernetics_system.dto.production.ProductionCostResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import dev.nerobeev.economic_cybernetics_system.exception.ComponentNotFoundException;
import dev.nerobeev.economic_cybernetics_system.mapper.ComponentMapper;
import dev.nerobeev.economic_cybernetics_system.repository.ComponentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComponentService {

  private final ComponentMapper componentMapper;
  private final ComponentRepository componentRepository;
  private final MarkingGenerator markingGenerator;

  @Transactional
  public ComponentResponse createComponent(ComponentCreateRequest createRequest) {
    var component = componentMapper.toEntity(createRequest, markingGenerator);
    component.setUCode(markingGenerator.generate(MarkingType.COMPONENT));
    var savedComponent = componentRepository.save(component);
    return componentMapper.toResponse(savedComponent);
  }

  @Transactional(readOnly = true)
  public List<ComponentResponse> getAllComponents() {
    return componentRepository.findAll().stream()
                              .map(componentMapper::toResponse)
                              .toList();
  }

  public void deleteComponent(Long id) {
    var component = componentRepository.findById(id)
                                       .orElseThrow(() -> new ComponentNotFoundException(id));
    componentRepository.delete(component);
    log.info("Component with id: {}", component.getId() + " deleted");
  }

  // на вход - материалы,
  // на выход описание издержек, результат и вопрос - точно ли готов делать компонент
  public ProductionCostResponse calculateCostAndValidateReadiness(Set<Material> materials) {
    // ProductionCostResponse
    return null;
  }

}
