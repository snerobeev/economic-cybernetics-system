package dev.nerobeev.economic_cybernetics_system.utest;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentResponse;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Component;
import dev.nerobeev.economic_cybernetics_system.entity.Material;
import dev.nerobeev.economic_cybernetics_system.repository.ComponentRepository;
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

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
    properties = {"server.port=8081"})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DisplayName("ComponentService Integration Test")
class ComponentServiceWithContextTest {
  @LocalServerPort
  private int port;
  @Autowired
  private ComponentService componentService;
  @Autowired
  private ComponentRepository componentRepository;
  private ComponentCreateRequest createRequest;
  private ComponentResponse componentResponse;
  private Material steel;
  private Set<Material> materials;

  @BeforeEach
  void setUp() {
    componentRepository.deleteAll();

    this.materials = new HashSet<>();
    steel = new Material(
        "Ginza",
        "Japan",
        100L
    );
    materials.add(steel);

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
        this.materials
    );
  }

  @AfterEach
  void tearDown() {
    componentRepository.deleteAll();
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
                                .map(MaterialResponse::producer)).containsExactly("Japan");
    assertThat(componentResponse.materials().stream()
                                .map(MaterialResponse::costPerUnit)).containsExactly(200L);

    Component savedComponent = componentRepository.findById(componentResponse.id()).orElse(null);
    assertThat(savedComponent).isNotNull();
    assertThat(savedComponent.getName()).isEqualTo("SSD диск Kingston");
    assertThat(savedComponent.getUCode()).isEqualTo(componentResponse.uCode());

    assertThat(savedComponent.getMaterials()).doesNotContainNull();
    assertThat(savedComponent.getMaterials()).hasSize(1);
  }

  @Test
  @DisplayName("Must successfully generate unique uCode for every new component")
  void shouldGenerateUniqueUCodeForEachComponent() {

    dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest createRequest2 =
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
  @DisplayName("")
  void calculateCostAndValidateReadinessTest() {

  }
}
