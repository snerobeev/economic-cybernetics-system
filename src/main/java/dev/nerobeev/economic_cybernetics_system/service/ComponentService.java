package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.newv.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.newv.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentResponse;
import dev.nerobeev.economic_cybernetics_system.mapper.ComponentMapper;
import dev.nerobeev.economic_cybernetics_system.repository.ComponentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComponentService {

    private final ComponentMapper componentMapper;
    private final ComponentRepository componentRepository;
    private final MarkingGenerator markingGenerator;

    public ComponentResponse createComponent(ComponentCreateRequest createRequest) {
        var component = componentMapper.toEntity(createRequest,markingGenerator);
        component.setUCode(markingGenerator.generate(MarkingType.COMPONENT));
        var savedComponent = componentRepository.save(component);
        return componentMapper.toResponse(savedComponent);
    }

    public List<ComponentResponse> getAllComponents() {
        return componentRepository.findAll().stream()
                .map(componentMapper::toResponse)
                .toList();
    }

}
