package dev.nerobeev.economic_cybernetics_system.integration;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import dev.nerobeev.economic_cybernetics_system.repository.MaterialRepository;
import dev.nerobeev.economic_cybernetics_system.service.MaterialService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

  @BeforeEach
  void setUp() {
    materialRepository.deleteAll();

    materialCreateRequest = new MaterialCreateRequest(
        "Steel Alloy X1",              // name
        UnitOfMeasure.TON,                  // unit
        150L,                               // costPerUnit
        180L,                               // pricePerUnit
        "Severstal",                        // producer
        500L,                               // quantity
        Status.RAW,                         // status
        IndustryCode.ENERGY,                // industryCode
        "Q4-2025",                          // planPeriod
        LocalDate.now(),                    // productionDate
        true                                // strategic
    );

    var saved = materialRepository.save(materialCreateRequest); // todo @Entity Material MaterialResoponse?
  }

  @AfterEach
  void tearDown() {
    materialRepository.deleteAll();
  }

  @Test()
  void calculateCostOfMaterial() {
    String materialName = "Steel Alloy X1";
    String productionCostName = "Steel Alloy X1";

    materialService.calculateCostOfMaterial(materialName,productionCostName);
  }
}
