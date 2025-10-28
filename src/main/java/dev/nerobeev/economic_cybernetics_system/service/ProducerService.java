package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialUpdateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.producer.ProducerCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.producer.ProducerResponse;
import dev.nerobeev.economic_cybernetics_system.dto.producer.ProducerUpdateRequest;
import dev.nerobeev.economic_cybernetics_system.entity.Producer;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import dev.nerobeev.economic_cybernetics_system.entity.ProductionCost;
import dev.nerobeev.economic_cybernetics_system.exception.*;
import dev.nerobeev.economic_cybernetics_system.mapper.MaterialMapper;
import dev.nerobeev.economic_cybernetics_system.mapper.ProducerMapper;
import dev.nerobeev.economic_cybernetics_system.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;
    private final MaterialRepository materialRepository;
    private final ComponentRepository componentRepository;
    private final ProductionCostRepository productionCostRepository;
    private final MarkingGenerator markingGenerator;
    private final MaterialMapper materialMapper;


    public ProducerResponse createProducer(ProducerCreateRequest request) {
     var producer = producerMapper.toEntity(request);
     producerRepository.save(producer);
     log.info("Producer with ID: {}", producer.getId() + " created");
     return producerMapper.toResponse(producer);
    }

    public List<ProducerResponse> getAllProducers() {
        return producerRepository.findAll().stream()
                .map(producerMapper::toResponse)
                .toList();
    }

    public ProducerResponse getProducerById(Long id) {
        var producer = producerRepository.findById(id)
                .orElseThrow(() -> new ProducerNotFoundException(id));
        return producerMapper.toResponse(producer);
    }

    public void deleteProducer(Long id) {
        var producer = producerRepository.findById(id)
                .orElseThrow(() -> new ProducerNotFoundException(id));
        producerRepository.delete(producer);
        log.info("Producer with id: {}", producer.getId() + " deleted.");
    }




}
