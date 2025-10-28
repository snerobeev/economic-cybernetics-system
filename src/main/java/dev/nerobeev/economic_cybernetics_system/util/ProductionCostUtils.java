package dev.nerobeev.economic_cybernetics_system.util;

import dev.nerobeev.economic_cybernetics_system.repository.MaterialRepository;
import dev.nerobeev.economic_cybernetics_system.repository.ProductionCostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductionCostUtils {

    private final ProductionCostRepository productionCostRepository;
    private final MaterialRepository materialRepository;

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
