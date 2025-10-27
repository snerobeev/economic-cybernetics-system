package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Producer;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import dev.nerobeev.economic_cybernetics_system.entity.ProductionCost;
import dev.nerobeev.economic_cybernetics_system.exception.CompanyNotFoundException;
import dev.nerobeev.economic_cybernetics_system.exception.MaterialByNameNotFoundException;
import dev.nerobeev.economic_cybernetics_system.exception.MaterialNotFoundException;
import dev.nerobeev.economic_cybernetics_system.exception.ProductionCostByNameNotFoundException;
import dev.nerobeev.economic_cybernetics_system.mapper.MaterialMapper;
import dev.nerobeev.economic_cybernetics_system.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {
  private final CompanyRepository companyRepository;
  private final MaterialRepository materialRepository;
  private final ComponentRepository componentRepository;
  private final ProductRepository productRepository;
  private final ProductionCostRepository productionCostRepository;
  private final MarkingGenerator markingGenerator;
  private final MaterialMapper materialMapper;

  public MaterialResponse produceMaterial(Long companyId, MaterialCreateRequest request) {
    var name = request.name();
    Producer producer = companyRepository.findById(companyId)
                                         .orElseThrow(() -> new CompanyNotFoundException(companyId));

    Material material = materialRepository.findMaterialByName(name)
                                          .orElseThrow(() -> new MaterialByNameNotFoundException(name));

    ProductionCost productionCost = productionCostRepository.findCostByName(name)
                                                            .orElseThrow(() -> new ProductionCostByNameNotFoundException(
                                                                request.name()));

    material.setName(name);
    material.setProducer(producer.getName()); // установка имени производителя (имя затрат == имя производителя)
    generateCode(material,request);           // нанесение маркировки
    var totalCost = computeTotalCost(name);   // сумма затрат за единицу
    material.setCostPerUnit(totalCost);       // установка суммы затрат
    var totalPricePerUnit = computePricePerUnit(name); // добавленная стоимость за ед
    material.setPricePerUnit(totalPricePerUnit); // установка добавленной стоимости


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

  public Long computePricePerUnit(String prodCostName){
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
