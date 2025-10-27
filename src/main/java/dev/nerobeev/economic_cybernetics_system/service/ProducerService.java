package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialUpdateRequest;
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

    public ProducerResponse updateMaterial(Long id, ProducerUpdateRequest updateRequest) {
        var producer = producerRepository.findById(id)
                .orElseThrow(() -> new ProducerNotFoundException(id));
        producerMapper.updateEntity(producer, updateRequest);
        var updateProducer = producerRepository.save(producer);
        return producerMapper.toResponse(updateProducer);
    }

    public void deleteProducer(Long id) {
        var producer = producerRepository.findById(id)
                .orElseThrow(() -> new ProducerNotFoundException(id));
        producerRepository.delete(producer);
        log.info("Producer with id: {}", producer.getId() + " deleted.");
    }


    public MaterialResponse produceMaterial(Long companyId, MaterialCreateRequest request) {
        var name = request.name();
        Producer producer = producerRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException(companyId));

        Material material = materialRepository.findMaterialByName(name)
                .orElseThrow(() -> new MaterialByNameNotFoundException(name));

        ProductionCost productionCost = productionCostRepository.findCostByName(name)
                .orElseThrow(() -> new ProductionCostByNameNotFoundException(
                        request.name()));

        material.setName(name);
        generateCode(material, request);           // нанесение маркировки
        var totalCost = computeTotalCost(name);   // сумма затрат за единицу
        material.setCostPerUnit(totalCost);       // установка суммы затрат
        var totalPricePerUnit = computePricePerUnit(name); // добавленная стоимость за ед
        material.setPricePerUnit(totalPricePerUnit); // установка добавленной стоимости
        material.setProducer(producer.getName()); // установка имени производителя (имя затрат == имя производителя)


        var upDateProducedMaterial = materialRepository.save(material); // сохранение в БД

        return null;
    }

    // Метод нанесения маркировки
    public Material generateCode(Material material, MaterialCreateRequest request) {
        if (material == null || request == null) {
            throw new IllegalArgumentException("Material or MaterialRequest is null");
        }
        material.setCode(markingGenerator.generate(MarkingType.MATERIAL));
        var markedMaterial = materialMapper.toEntity(request, markingGenerator);
        materialRepository.save(markedMaterial);
        return markedMaterial;

    }

    public Long computePricePerUnit(String prodCostName) {
        var totalCost = computeTotalCost(prodCostName);
        return totalCost / 50; // пока так
    }

    // Суммирует все Затраты Материала (у каждого вида Материала - свои Затраты)
    public Long computeTotalCost(String prodCostName) {
        var result = productionCostRepository.findCostByName(prodCostName).stream()
                .mapToLong(v ->
                        v.getEnergyCost() +
                                v.getLaborHours() +
                                v.getEquipmentCost() +
                                v.getMaterialCost() +
                                v.getLogisticsCost() +
                                v.getLicenseCost() +
                                v.getTaxCost() +
                                v.getSocialCost() +
                                v.getAmortizationCost() +
                                v.getEquipmentMaintenanceCost() +
                                v.getAdministrativeCost() +
                                v.getRentalCost() +
                                v.getCommunicationCost() +
                                v.getInsuranceCost() +
                                v.getResearchAndDevelopmentCost() +
                                v.getInterestCost() +
                                v.getEcoCost());

        return result.sum();
    }

}
