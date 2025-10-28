package dev.nerobeev.economic_cybernetics_system.integration;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.production.ProductionCostCreateRequest;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import dev.nerobeev.economic_cybernetics_system.exception.MaterialNotFoundException;
import dev.nerobeev.economic_cybernetics_system.repository.MaterialRepository;
import dev.nerobeev.economic_cybernetics_system.service.MaterialService;
import dev.nerobeev.economic_cybernetics_system.service.ProductionCostService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port=8081"})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DisplayName("ProductionCostService Integration Test")
class MaterialServiceTest {

    private Material material;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private MaterialService materialService;
    private MaterialCreateRequest materialCreateRequest;
    private ProductionCostCreateRequest productionCostCreateRequest;
    @Autowired
    private ProductionCostService productionCostService;

    @BeforeEach
    void setUp() {
        materialRepository.deleteAll();

        materialCreateRequest = new MaterialCreateRequest(
                "Steel Alloy X1",             // name
                UnitOfMeasure.TON,                  // unit
                "Severstal",                        // producer
                500L,                               // quantity
                Status.RAW,                         // status
                IndustryCode.ENERGY,                // industryCode
                "Q4-2025",                          // planPeriod
                LocalDate.now(),                    // productionDate
                true                                // strategic
        );

        productionCostCreateRequest = new ProductionCostCreateRequest(
                "Steel Alloy X1", // name
                10L,  // energyCost
                10L,  // laborHours
                10L,  // equipmentCost
                10L,  // materialCost
                10L,  // logisticsCost
                10L,  // licenseCost
                10L,  // taxCost
                10L,  // socialCost
                10L,  // amortizationCost
                10L,  // equipmentMaintenanceCost
                10L,  // administrativeCost
                10L,  // rentalCost
                10L,  // communicationCost
                10L,  // insuranceCost
                10L,  // researchAndDevelopmentCost
                10L,  // interestCost
                10L   // ecoCost
        );
    }

    @AfterEach
    void tearDown() {
        materialRepository.deleteAll();
    }

    @Test()
    @DisplayName("Positive calculate and update material costs.")
    void calculatesMaterialCostSuccessfully_Test() {
        var prodCostResponse = productionCostService.createProductionCost(productionCostCreateRequest);
        var materialResponse = materialService.createMaterial(materialCreateRequest);
        var materialName = materialResponse.name();
        var prodCostName = prodCostResponse.name();

        var result = materialService.calculateCostPerUnit(materialName);
        var costPerUnit = materialRepository.findMaterialByName(materialName).stream()
                .findFirst()
                .map(Material::getCostPerUnit)
                .orElseThrow(() -> new MaterialNotFoundException(materialResponse.id()));

        var result2= materialService.calculatePricePerUnit(materialName);
        var pricePerUnit = materialRepository.findMaterialByName(materialName).stream()
                        .findFirst()
                        .map(Material::getPricePerUnit)
                        .orElseThrow(() -> new MaterialNotFoundException(materialResponse.id()));

        System.out.println("-----------RESULT CostPerUnit--------------- " + result);
        Assertions.assertEquals(materialName, prodCostName);
        Assertions.assertEquals(costPerUnit, result);

        System.out.println("-----------RESULT2 PricePerUnit------------- " + result2);
        Assertions.assertEquals(materialName, prodCostName);
        Assertions.assertEquals(pricePerUnit,result2);
    }
}
