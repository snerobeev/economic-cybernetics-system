package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialUpdateRequest;
import dev.nerobeev.economic_cybernetics_system.exception.MaterialNotFoundException;
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

    public MaterialResponse createMaterial(MaterialCreateRequest request) {
        var material = materialMapper.toEntity(request, markingGenerator);
        material.setName(request.name());
        material.setUnit(request.unit());
        var totalCost = computeTotalProdCost(request.name());        // сумма затрат за единицу
        material.setCostPerUnit(totalCost);                                // установка суммы затрат
        var totalPricePerUnit = computePricePerUnit(request.name()); // добавленная стоимость за ед
        material.setPricePerUnit(totalPricePerUnit);                       // установка добавленной стоимости
        material.setProducer(request.producer());
        material.setQuantity(request.quantity());
        material.setStatus(request.status());
        material.setIndustryCode(request.industryCode());
        material.setPlanPeriod(request.planPeriod());
        material.setProductionDate(request.productionDate());
        material.setStrategic(request.strategic());
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
        var material = materialRepository.findMaterialByName(materialName)
                .orElseThrow();
        var materialWithCostPerUnit = materialRepository.save(material);
        return materialWithCostPerUnit.getCostPerUnit();
    }
    // расчет добавленной стоимости Материала
    public Long computePricePerUnit(String prodCostName) {
        var totalCost = computeTotalProdCost(prodCostName);
        var divide = totalCost / 2;
        return totalCost + divide; // пока так
    }

    // Суммирует все Затраты Материала (у каждого вида Материала - свои Затраты)
    public Long computeTotalProdCost(String prodCostName) {
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
