package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
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

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "energy_cost")
  private BigDecimal energyCost;           // Стоимость энергии

  @Column(name = "labor_hours")
  private BigDecimal laborHours;           // Зарплата

  @Column(name = "equipment_cost")
  private BigDecimal equipmentCost;        // Стоимость оборудования

  @Column(name = "material_cost")
  private BigDecimal materialCost;         // Стоимость расходных материалов

  @Column(name = "logistics_cost")
  private BigDecimal logisticsCost;        // Стоимость доставки

  @Column(name = "license_cost")
  private BigDecimal licenseCost;          // Стоимость лицензий

  @Column(name = "tax_cost")
  private BigDecimal taxCost;              // Налоги

  @Column(name = "social_cost")
  private BigDecimal socialCost;           // Социальные выплаты

  @Column(name = "amortization_cost")
  private BigDecimal amortizationCost;     // Амортизация

  @Column(name = "equipment_maintenance_cost")
  private BigDecimal equipmentMaintenanceCost; // Обслуживание оборудования

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
