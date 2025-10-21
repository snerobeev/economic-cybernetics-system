package dev.nerobeev.economic_cybernetics_system.utest;


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

  @BeforeEach
  void setUp() {
    productionCostRepository.deleteAll();
    productionCostCreateRequest = new ProductionCostCreateRequest(
        "Производство Компонента X1", // name
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
  }

  @AfterEach
  void tearDown() {
    productionCostRepository.deleteAll();
  }

  @Test
  @DisplayName("Successful create productionCost and save it in DB")
  void getCreateProductionCostAndSaveInDBTest() {

    var productionCostResponse = productionCostService
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
    assertThat(savedProductionCost.getName()).isEqualTo("Производство Компонента X1");
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

//    var savedProductionCost = productionCostService.createProductionCost(productionCostCreateRequest);
//    var result = productionCostService.computeTotalCost();

  }

}
