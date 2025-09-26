package dev.nerobeev.economic_cybernetics_system.utest.entity;

import dev.nerobeev.economic_cybernetics_system.entity.EconomicSector;
import dev.nerobeev.economic_cybernetics_system.entity.PlanIndicator;
import dev.nerobeev.economic_cybernetics_system.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Product Tests")
class ProductTest {
    private EconomicSector sector;
    private Product plank;
    private PlanIndicator plan;

    @BeforeEach
    void setup() {
        sector = new EconomicSector("Деревообработка", "02", "Отрасль");
        plank = new Product("Доска обрезная 40", "5 м", sector);
        plan = PlanIndicator.builder()
                .product(plank)
                .planYear(2025)
                .planQuarter(3)
                .plannedOutput(new BigDecimal(1000))
                .actualOutput(new BigDecimal(1030))
                .build();

        plank.getPlanIndicators().add(plan);
    }

    @Test
    @DisplayName("Should create product with relationships")
    void shouldCreateProductWithRelationships() {
        assertEquals("Доска обрезная 40", plank.getName());
        assertEquals("5 м", plank.getUnit());
        assertEquals(sector, plank.getSector());
        assertEquals(1,plank.getPlanIndicators().size());
    }

    @Test
    @DisplayName("Should support builder pattern")
    void shouldSupportBuilderPattern() {
        Product steel = Product.builder()
                .name("Сталь")
                .unit("тонн")
                .sector(sector)
                .build();

        assertEquals("Сталь", steel.getName());
        assertEquals("тонн", steel.getUnit());
        assertEquals(sector, steel.getSector());
        assertNotNull(steel.getPlanIndicators());
    }

    @Test
    @DisplayName("Should maintain plan indicator relationships")
    void shouldMaintainPlanIndicatorRelationships() {
        assertEquals(plan, plank.getPlanIndicators().getFirst());
        assertEquals(plank, plan.getProduct());
    }

}
