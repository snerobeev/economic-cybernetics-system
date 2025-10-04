package dev.nerobeev.economic_cybernetics_system.utest.entity;

import dev.nerobeev.economic_cybernetics_system.domain.newv.UnitOfMeasure;
import dev.nerobeev.economic_cybernetics_system.entity.PlanIndicator;
import dev.nerobeev.economic_cybernetics_system.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Product Tests")
class ProductTest {
    private Sector sector;
    private Product plank;
    private PlanIndicator plan;


    @BeforeEach
    void setup() {
        sector = new Sector("Деревообработка", "02", "Отрасль");
    }

    @Test
    @DisplayName("Should create product with relationships")

    void shouldCreateProductWithRelationships() {
        assertEquals("Доска обрезная 40", plank.getName());
        assertEquals("5 м", plank.getUnit());

    }

    @Test
    @DisplayName("Should support builder pattern")
    void shouldSupportBuilderPattern() {
        Product steel = Product.builder()
                .name("Сталь")
                .unit(UnitOfMeasure.CUBIC_METER)
                .build();

        assertEquals("Сталь", steel.getName());
        assertEquals("тонн", steel.getUnit());

    }

    @Test
    @DisplayName("Should maintain plan indicator relationships")
    void shouldMaintainPlanIndicatorRelationships() {
        assertEquals(plank, plan.getProduct());
    }

}
