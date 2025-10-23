package dev.nerobeev.economic_cybernetics_system.integration;


import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.production.ProductionCostCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.production.ProductionCostResponse;
import dev.nerobeev.economic_cybernetics_system.entity.ProductionCost;
import dev.nerobeev.economic_cybernetics_system.repository.ProductionCostRepository;
import dev.nerobeev.economic_cybernetics_system.service.ProductionCostService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port=8081"})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DisplayName("ProductionCostService Integration Test")
class ProductionCostServiceTest {
    @LocalServerPort
    private int port;
    @Autowired
    private ProductionCostRepository productionCostRepository;
    @Autowired
    private ProductionCostService productionCostService;
    private ProductionCostCreateRequest productionCostCreateRequest;
    private ProductionCostResponse productionCostResponse;
    private MaterialCreateRequest steel;
    private MaterialCreateRequest rock;

    @BeforeEach
    void setUp() {
        productionCostRepository.deleteAll();

        productionCostCreateRequest = new ProductionCostCreateRequest(
                "SSD 25", // name
                200L,  // energyCost
                100L,  // laborHours
                50L,   // equipmentCost
                200L,  // materialCost
                25L,   // logisticsCost
                30L,   // licenseCost
                15L,   // taxCost
                5L,    // socialCost
                40L,   // amortizationCost
                10L,   // equipmentMaintenanceCost
                70L,   // administrativeCost
                60L,   // rentalCost
                12L,   // communicationCost
                8L,    // insuranceCost
                1250L, // researchAndDevelopmentCost
                55L,   // interestCost
                20L    // ecoCost
        );

       steel = new MaterialCreateRequest(
          "Steel Alloy X1",             // name
          UnitOfMeasure.TON,                  // unit
          0L,                                 // costPerUnit
          180L,                               // pricePerUnit
          "Severstal",                        // producer
          500L,                               // quantity
          Status.RAW,                         // status
          IndustryCode.ENERGY,                // industryCode
          "Q4-2025",                          // planPeriod
          LocalDate.now(),                    // productionDate
          true                                // strategic
      );

      rock = new MaterialCreateRequest(
          "Steel Alloy X1",             // name
          UnitOfMeasure.TON,                  // unit
          0L,                                 // costPerUnit
          180L,                               // pricePerUnit
          "Severstal",                        // producer
          500L,                               // quantity
          Status.RAW,                         // status
          IndustryCode.ENERGY,                // industryCode
          "Q4-2025",                          // planPeriod
          LocalDate.now(),                    // productionDate
          true                                // strategic
      );
    }

    @AfterEach
    void tearDown() {
        productionCostRepository.deleteAll();
    }

    @Test
    @DisplayName("Successful create productionCost and save it in DB")
    void getCreateProductionCostAndSaveInDBTest() {

        productionCostResponse = productionCostService
                .createProductionCost(productionCostCreateRequest);

        assertThat(productionCostResponse).isNotNull();
        assertThat(productionCostResponse.energyCost()).isEqualTo(200L);
        assertThat(productionCostResponse.laborHours()).isEqualTo(100L);
        assertThat(productionCostResponse.equipmentCost()).isEqualTo(50L);
        assertThat(productionCostResponse.materialCost()).isEqualTo(200L);
        assertThat(productionCostResponse.logisticsCost()).isEqualTo(25L);
        assertThat(productionCostResponse.licenseCost()).isEqualTo(30L);
        assertThat(productionCostResponse.taxCost()).isEqualTo(15L);
        assertThat(productionCostResponse.socialCost()).isEqualTo(5L);
        assertThat(productionCostResponse.amortizationCost()).isEqualTo(40L);
        assertThat(productionCostResponse.equipmentMaintenanceCost()).isEqualTo(10L);
        assertThat(productionCostResponse.administrativeCost()).isEqualTo(70L);
        assertThat(productionCostResponse.rentalCost()).isEqualTo(60L);
        assertThat(productionCostResponse.communicationCost()).isEqualTo(12L);
        assertThat(productionCostResponse.insuranceCost()).isEqualTo(8L);
        assertThat(productionCostResponse.researchAndDevelopmentCost()).isEqualTo(1250L);
        assertThat(productionCostResponse.interestCost()).isEqualTo(55L);
        assertThat(productionCostResponse.ecoCost()).isEqualTo(20L);

        ProductionCost savedProductionCost = productionCostRepository
                .findById(productionCostResponse.id()).orElse(null);

        assertThat(savedProductionCost).isNotNull();
        assertThat(savedProductionCost.getId()).isNotNull();
        assertThat(savedProductionCost.getName()).isEqualTo("SSD 25");
        assertThat(savedProductionCost.getEnergyCost()).isEqualTo(200L);
        assertThat(savedProductionCost.getLaborHours()).isEqualTo(100L);
        assertThat(savedProductionCost.getEquipmentCost()).isEqualTo(50L);
        assertThat(savedProductionCost.getMaterialCost()).isEqualTo(200L);
        assertThat(savedProductionCost.getLogisticsCost()).isEqualTo(25L);
        assertThat(savedProductionCost.getLicenseCost()).isEqualTo(30L);
        assertThat(savedProductionCost.getTaxCost()).isEqualTo(15L);
        assertThat(savedProductionCost.getSocialCost()).isEqualTo(5L);
        assertThat(savedProductionCost.getAmortizationCost()).isEqualTo(40L);
        assertThat(savedProductionCost.getEquipmentMaintenanceCost()).isEqualTo(10L);
        assertThat(savedProductionCost.getAdministrativeCost()).isEqualTo(70L);
        assertThat(savedProductionCost.getRentalCost()).isEqualTo(60L);
        assertThat(savedProductionCost.getCommunicationCost()).isEqualTo(12L);
        assertThat(savedProductionCost.getInsuranceCost()).isEqualTo(8L);
        assertThat(savedProductionCost.getResearchAndDevelopmentCost()).isEqualTo(1250L);
        assertThat(savedProductionCost.getInterestCost()).isEqualTo(55L);
        assertThat(savedProductionCost.getEcoCost()).isEqualTo(20L);
    }

    @Test
    @DisplayName("Successful summation of production costs")
    void getComputeProductionCostTest() {
        var expectedName = "SSD 25";
        var prodCost = productionCostService.createProductionCost(productionCostCreateRequest);
        var resultTotalCost = productionCostService.computeTotalCost(expectedName);

        assertThat(resultTotalCost).isNotNull();
        assertThat(resultTotalCost).isEqualTo(2150L);
        Assertions.assertEquals(expectedName, prodCost.name());

    }

    @Test
  @DisplayName(" ")
  void getTotalCostPerUnitFromAllMaterialsTest() {

    }

}
