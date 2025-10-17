package dev.nerobeev.economic_cybernetics_system.utest;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentResponse;
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
  }
}
