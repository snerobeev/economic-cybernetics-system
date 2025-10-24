package dev.nerobeev.economic_cybernetics_system.integration;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentResponse;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Component;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import dev.nerobeev.economic_cybernetics_system.exeption.MaterialNotFoundException;
import dev.nerobeev.economic_cybernetics_system.repository.ComponentRepository;
import dev.nerobeev.economic_cybernetics_system.repository.MaterialRepository;
import dev.nerobeev.economic_cybernetics_system.service.ComponentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
    properties = {"server.port=8081"})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DisplayName("ComponentService Integration Test")
class ComponentServiceTest {
  @LocalServerPort
  private int port;
  @Autowired
  private ComponentService componentService;
  @Autowired
  private ComponentRepository componentRepository;
  @Autowired
  private MaterialRepository materialRepository;
  private ComponentCreateRequest createRequest;

  @BeforeEach
  void setUp() {
    componentRepository.deleteAll();
    materialRepository.deleteAll();

    Set<Material> materials = new HashSet<>();
    Material steel = Material.builder()
                             .name("Ginza")
                             .code("MAT-20251003-001")
                             .unit(UnitOfMeasure.TON)
                             .costPerUnit(100L)
                             .pricePerUnit(180L)
                             .producer("Japan")
                             .quantity(180L)
                             .status(Status.RAW)
                             .industryCode(IndustryCode.ENERGY)
                             .planPeriod("Q4-2025")
                             .productionDate(LocalDate.now())
                             .strategic(true)
                             .build();
    materials.add(steel);

    Material aluminium = Material.builder()
                                 .name("Aluminium C")
                                 .code("MAT-20251003-002")
                                 .unit(UnitOfMeasure.TON)
                                 .costPerUnit(50L)
                                 .pricePerUnit(80L)
                                 .producer("Russia")
                                 .quantity(100L)
                                 .status(Status.RAW)
                                 .industryCode(IndustryCode.ENERGY)
                                 .planPeriod("Q4-2025")
                                 .productionDate(LocalDate.now())
                                 .strategic(true)
                                 .build();

    materials.add(steel);
    materials.add(aluminium);

    createRequest = new ComponentCreateRequest(
        "SSD диск Kingston",
        UnitOfMeasure.PCS,
        4500L,
        6500L,
        "Kingston",
        150L,
        Status.COMPONENT,
        IndustryCode.TRANSPORT,
        "Q2-2025",
        LocalDate.of(2025, 2, 10),
        true,
        materials
    );
  }

  @AfterEach
  void tearDown() {
    componentRepository.deleteAll();
    materialRepository.deleteAll();
  }

  @Test
  void test() {
    System.out.println("Приложение запущено на порту: " + port);
    System.out.println("H2 Console: http://localhost:" + port + "/h2-console");
  }

  @Test
  @DisplayName("Must successfully create new component and save it to DB")
  void shouldCreateComponentAndSaveToDataBaseTest() {

    var componentResponse = componentService.createComponent(createRequest);

    assertThat(componentResponse).isNotNull();
    assertThat(componentResponse.id()).isNotNull();
    assertThat(componentResponse.name()).isEqualTo("SSD диск Kingston");
    assertThat(componentResponse.uCode()).isNotNull();
    assertThat(componentResponse.uCode()).startsWith("CMP-");
    assertThat(componentResponse.producer()).isEqualTo("Kingston");
    assertThat(componentResponse.costPerUnit()).isEqualTo(4500L);
    assertThat(componentResponse.pricePerUnit()).isEqualTo(6500L);
    assertThat(componentResponse.quantity()).isEqualTo(150L);
    assertThat(componentResponse.status()).isEqualTo(Status.COMPONENT);
    assertThat(componentResponse.industryCode()).isEqualTo(IndustryCode.TRANSPORT);
    assertThat(componentResponse.strategic()).isTrue();

    assertThat(componentResponse.materials().stream()
                                .anyMatch(m -> m.name().equals("Ginza"))).isTrue();
    assertThat(componentResponse.materials().stream()
                                .map(MaterialResponse::producer)).contains("Japan");
    assertThat(componentResponse.materials().stream()
                                .map(MaterialResponse::costPerUnit)).contains(100L);

    assertThat(componentResponse.materials().stream()
                                .anyMatch(m -> m.name().equals("Aluminium C"))).isTrue();
    assertThat(componentResponse.materials().stream()
                                .map(MaterialResponse::producer)).contains("Russia");
    assertThat(componentResponse.materials().stream()
                                .map(MaterialResponse::costPerUnit)).contains(100L);

    assertThat(componentResponse.materials())
        .hasSize(2)
        .extracting(MaterialResponse::name, MaterialResponse::producer, MaterialResponse::costPerUnit)
        .containsExactlyInAnyOrder(
            tuple("Aluminium C", "Russia", 50L),
            tuple("Ginza", "Japan", 100L)
        );

    Component savedComponent = componentRepository.findById(componentResponse.id()).orElse(null);
    assertThat(savedComponent).isNotNull();
    assertThat(savedComponent.getName()).isEqualTo("SSD диск Kingston");
    assertThat(savedComponent.getUCode()).isEqualTo(componentResponse.uCode());

    assertThat(savedComponent.getMaterials()).doesNotContainNull();
    assertThat(savedComponent.getMaterials()).hasSize(2);

  }

  @Test
  @DisplayName("Must successfully generate unique uCode for every new component")
  void shouldGenerateUniqueUCodeForEachComponent() {

    ComponentCreateRequest createRequest2 =
        new ComponentCreateRequest(
            "RAM DDR4",
            UnitOfMeasure.PCS,
            500L,
            650L,
            "Corsair",
            200L,
            Status.COMPONENT,
            IndustryCode.TRANSPORT,
            "Q2-2025",
            LocalDate.of(2025, 4, 29),
            false,
            new HashSet<>()
        );
    ComponentResponse response = componentService.createComponent(createRequest);
    ComponentResponse response2 = componentService.createComponent(createRequest2);

    assertThat(response.uCode()).isNotNull();
    assertThat(response2.uCode()).isNotNull();
    assertThat(response.uCode()).isNotEqualTo(response2.uCode());

    // формат uCode
    assertThat(response.uCode()).matches("CMP-\\d{8}-\\d{3}");
    assertThat(response2.uCode()).matches("CMP-\\d{8}-\\d{3}");
  }

  @Test
  @DisplayName("Testing by .usingRecursiveComparison")
  void equalsSetMaterials() {

    Material expectedSteel = Material.builder()
                                     .name("Ginza")
                                     .code("MAT-20251003-001")
                                     .unit(UnitOfMeasure.TON)
                                     .costPerUnit(100L)
                                     .pricePerUnit(180L)
                                     .producer("Japan")
                                     .quantity(180L)
                                     .status(Status.RAW)
                                     .industryCode(IndustryCode.ENERGY)
                                     .planPeriod("Q4-2025")
                                     .productionDate(LocalDate.now())
                                     .strategic(true)
                                     .build();
    materialRepository.save(expectedSteel);

    var actualSteel = materialRepository.findMaterialById(expectedSteel.getId())
                                        .orElseThrow(() -> new MaterialNotFoundException(expectedSteel.getId()));

    assertThat(actualSteel)
        .usingRecursiveComparison()
        .ignoringFields()
        .isEqualTo(expectedSteel);
  }

}
