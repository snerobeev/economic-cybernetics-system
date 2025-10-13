package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
  private Long energyCost;           // Стоимость энергии

  @NotNull
  @Column(name = "labor_hours")
  private Long laborHours;           // Зарплата

  @NotNull
  @Column(name = "equipment_cost")
  private Long equipmentCost;        // Стоимость оборудования

  @NotNull
  @Column(name = "material_cost")
  private Long materialCost;         // Стоимость расходных материалов

  @Column(name = "logistics_cost")
  private Long logisticsCost;        // Стоимость доставки

  @NotNull
  @Column(name = "license_cost")
  private Long licenseCost;          // Стоимость лицензий

  @NotNull
  @Column(name = "tax_cost")
  private Long taxCost;              // Налоги

  @NotNull
  @Column(name = "social_cost")
  private Long socialCost;           // Социальные выплаты

  @NotNull
  @Column(name = "amortization_cost")
  private Long amortizationCost;     // Амортизация

  @NotNull
  @Column(name = "equipment_maintenance_cost")
  private Long equipmentMaintenanceCost; // Обслуживание оборудования

  @NotNull
  @Column(name = "administrative_cost")
  private Long administrativeCost;   // Административные расходы

  @Column(name = "rental_cost")
  private Long rentalCost;           // Аренда

  @Column(name = "communication_cost")
  private Long communicationCost;    // Связь

  @Column(name = "insurance_cost")
  private Long insuranceCost;        // Страхование

  @Column(name = "research_and_development_cost")
  private Long researchAndDevelopmentCost; // НИОКР

  @Column(name = "interest_cost")
  private Long interestCost;         // Кредиты и займы, расходы на маркетинг

  @Column(name = "eco_cost")
  private Long ecoCost;              // Экологический сбор

}
