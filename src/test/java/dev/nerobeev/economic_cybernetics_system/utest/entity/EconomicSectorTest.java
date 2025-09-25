package dev.nerobeev.economic_cybernetics_system.utest.entity;

import dev.nerobeev.economic_cybernetics_system.entity.EconomicSector;
import dev.nerobeev.economic_cybernetics_system.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Economic Sector Test")
class EconomicSectorTest {

  private EconomicSector agriculture;
  private Product wheat;

  @BeforeEach
  void setup() {
    agriculture = new EconomicSector("Сельское хозяйство", "01", "Сектор сельского хозяйства");
    wheat = new Product("Пшеница", "тонн", agriculture);
    agriculture.getProducts().add(wheat);
  }

  @Test
  @DisplayName("Should create sector with basic properties")
  void shouldCreateSectorWithBasicProperties() {
    var expected = "Деревообработка";
    var actual = agriculture.getName();

    assertNotEquals(expected, actual);

    assertEquals("Сельское хозяйство", agriculture.getName());
    assertEquals("01", agriculture.getCode());
    assertEquals("Сектор сельского хозяйства", agriculture.getDescription());
    assertNotNull(agriculture.getProducts());
  }

  @Test
  @DisplayName("Should maintain product relationships")
  void shouldMaintainProductRelationships() {
    assertEquals(1, agriculture.getProducts().size());
    assertEquals(wheat, agriculture.getProducts().getFirst());
    assertEquals(agriculture, wheat.getSector());
  }

  @Test
  @DisplayName("Should use Lombok generated methods")
  void souldUseLombokGeneratedMethods() {
    var economicSector1 = new EconomicSector("Test", "T1", "Test sector");
    var economicSector2 = new EconomicSector("Test", "T1", "Test sector");
    // Equals по коду благодаря @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    assertEquals(economicSector1,economicSector2);
    assertEquals(economicSector1.hashCode(),economicSector2.hashCode());
    // ToString работает
    String toString = agriculture.toString();
    assertTrue(toString.contains("Сельское хозяйство"));
    assertTrue(toString.contains("01"));
  }

  @Test
  @DisplayName("Should support Builder pattern")
  void shouldSupportBuilderPattern() {
    EconomicSector sector = EconomicSector.builder()
        .id(1L)
        .name("Металлургическая промышленность")
        .code("02")
        .description("Цветная металлургия")
        .build();

    assertEquals(1,sector.getId());
    assertEquals("Металлургическая промышленность",sector.getName());
    assertEquals("02",sector.getCode());
    assertEquals("Цветная металлургия",sector.getDescription());
    assertNotNull(sector.getProducts());
    assertTrue(sector.getProducts().isEmpty());
  }

}
