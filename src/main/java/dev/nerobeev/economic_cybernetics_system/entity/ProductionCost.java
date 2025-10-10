package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "production_costs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductionCost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull
  @Column(name = "energy_cost")
  private BigDecimal energyCost;           // Стоимость энергии

  @NotNull
  @Column(name = "labor_hours")
  private BigDecimal laborHours;           // Зарплата

  @NotNull
  @Column(name = "equipment_cost")
  private BigDecimal equipmentCost;        // Стоимость оборудования

  @NotNull
  @Column(name = "material_cost")
  private BigDecimal materialCost;         // Стоимость расходных материалов

  @Column(name = "logistics_cost")
  private BigDecimal logisticsCost;        // Стоимость доставки

  @NotNull
  @Column(name = "license_cost")
  private BigDecimal licenseCost;          // Стоимость лицензий

  @NotNull
  @Column(name = "tax_cost")
  private BigDecimal taxCost;              // Налоги

  @NotNull
  @Column(name = "social_cost")
  private BigDecimal socialCost;           // Социальные выплаты

  @NotNull
  @Column(name = "amortization_cost")
  private BigDecimal amortizationCost;     // Амортизация

  @NotNull
  @Column(name = "equipment_maintenance_cost")
  private BigDecimal equipmentMaintenanceCost; // Обслуживание оборудования

  @NotNull
  @Column(name = "administrative_cost")
  private BigDecimal administrativeCost;   // Административные расходы

  @Column(name = "rental_cost")
  private BigDecimal rentalCost;           // Аренда

  @Column(name = "communication_cost")
  private BigDecimal communicationCost;    // Связь

  @Column(name = "insurance_cost")
  private BigDecimal insuranceCost;        // Страхование

  @Column(name = "research_and_development_cost")
  private BigDecimal researchAndDevelopmentCost; // НИОКР

  @Column(name = "interest_cost")
  private BigDecimal interestCost;         // Кредиты и займы, расходы на маркетинг

  @Column(name = "eco_cost")
  private BigDecimal ecoCost;              // Экологический сбор

}
