package dev.nerobeev.economic_cybernetics_system.utest;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentResponse;
import dev.nerobeev.economic_cybernetics_system.entity.Component;
import dev.nerobeev.economic_cybernetics_system.repository.ComponentRepository;
import dev.nerobeev.economic_cybernetics_system.service.ComponentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DisplayName("ComponentService Integration Test")
//@Disabled("Включай вручную для отладки")
class ComponentServiceWithContextTest {
  @Autowired
  private ComponentService componentService;
  @Autowired
  private ComponentRepository componentRepository;
  private ComponentCreateRequest createRequest;
  private ComponentResponse componentResponse;

  @BeforeEach
  void setUp() {
    componentRepository.deleteAll();

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
        new HashSet<>(),
        new HashSet<>()
    );
  }

  @AfterEach
  void tearDown() {
    componentRepository.deleteAll();
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

    Component savedComponent = componentRepository.findById(componentResponse.id()).orElse(null);
    assertThat(savedComponent).isNotNull();
    assertThat(savedComponent.getName()).isEqualTo("SSD диск Kingston");
    assertThat(savedComponent.getUCode()).isEqualTo(componentResponse.uCode());
  }

  @Test
  @DisplayName("Must successfully generate unique uCode for every new component")
  void shouldGenerateUniqueUCodeForEachComponent() {

    dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest createRequest2 = new ComponentCreateRequest(
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
        new HashSet<>(),
        new HashSet<>()
    );
    ComponentResponse response1 = componentService.createComponent(createRequest);
    ComponentResponse response2 = componentService.createComponent(createRequest2);

    assertThat(response1.uCode()).isNotNull();
    assertThat(response2.uCode()).isNotNull();
    assertThat(response1.uCode()).isNotEqualTo(response2.uCode());

    // формат uCode
    assertThat(response1.uCode()).matches("CMP-\\d{8}-\\d{3}");
    assertThat(response2.uCode()).matches("CMP-\\d{8}-\\d{3}");
  }
}
