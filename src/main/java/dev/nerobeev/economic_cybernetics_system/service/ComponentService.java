package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentResponse;
import dev.nerobeev.economic_cybernetics_system.exeption.ComponentNotFoundException;
import dev.nerobeev.economic_cybernetics_system.mapper.ComponentMapper;
import dev.nerobeev.economic_cybernetics_system.repository.ComponentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComponentService {

  private final ComponentMapper componentMapper;
  private final ComponentRepository componentRepository;
  private final MarkingGenerator markingGenerator;

  public ComponentResponse createComponent(ComponentCreateRequest createRequest) {
    var component = componentMapper.toEntity(createRequest, markingGenerator);
    component.setUCode(markingGenerator.generate(MarkingType.COMPONENT));
    var savedComponent = componentRepository.save(component);
    return componentMapper.toResponse(savedComponent);
  }

  public List<ComponentResponse> getAllComponents() {
    return componentRepository.findAll().stream()
                              .map(componentMapper::toResponse)
                              .toList();
  }

  public void deleteComponent(Long id){
    var component = componentRepository.findById(id)
        .orElseThrow(() -> new ComponentNotFoundException(id));
    componentRepository.delete(component);
    log.info("Component with id: {}", component.getId() + " deleted");
  }

}
