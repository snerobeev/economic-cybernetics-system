package dev.nerobeev.economic_cybernetics_system.utest.entity;

import dev.nerobeev.economic_cybernetics_system.entity.EconomicSector;
import dev.nerobeev.economic_cybernetics_system.entity.PlanIndicator;
import dev.nerobeev.economic_cybernetics_system.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Plan Indicator Tests")
public class PlanIndicatorTest {

    private Product product;
    private PlanIndicator indicator;

    @BeforeEach
    void setUp() {
        EconomicSector sector = new EconomicSector("Тест", "01", "Тестовый сектор");
        product = new Product("Тестовый продукт", "шт", sector);

        indicator = PlanIndicator.builder()
                .product(product)
                .planYear(2025)
                .planQuarter(1)
                .plannedOutput(new BigDecimal("1000"))
                .build();
    }

    @Nested
    @DisplayName("Deviation Calculations")
    class DeviationCalculations {
        @Test
        @DisplayName("Should calculate positive deviation correctly")
        void shouldCalculatePositiveDeviationCorrectly() {

            indicator.setActualOutput(new BigDecimal(1100));

            BigDecimal deviation = indicator.getDeviation();
            BigDecimal deviationPercent = indicator.getDeviationPercent();

            assertEquals(new BigDecimal(100),deviation);
        }
        @Test
        @DisplayName("Should calculate negative deviation correctly")
        void shouldCalculateNegativeDeviationCorrectly() {

        }
    }

}
