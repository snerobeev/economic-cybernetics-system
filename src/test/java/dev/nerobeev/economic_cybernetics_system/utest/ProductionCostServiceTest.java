package dev.nerobeev.economic_cybernetics_system.utest;

import dev.nerobeev.economic_cybernetics_system.repository.ProductionCostRepository;
import dev.nerobeev.economic_cybernetics_system.service.ProductionCostService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

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

  @BeforeEach
  void setUp() {
    productionCostRepository.deleteAll();
  }

  @AfterEach
  void tearDown() {
    productionCostRepository.deleteAll();
  }

  @Test
  @DisplayName("Successful summation of production costs")
  void getTotalCostTest(){
  var result = productionCostService.getTotalCost();
    System.out.println("Total cost is: " + result);

    assertThat(result).isNotNull();
  }

}
