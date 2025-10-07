package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductionCost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String name;
  @Column()
  private BigDecimal energyCost;           // Стоимость энергии
  @Column()
  private BigDecimal laborHours;           // Зарплата
  @Column()
  private BigDecimal equipmentCost;        // Стоимость оборудования
  @Column()
  private BigDecimal materialCost;         // Стоимость расходных материалов
  @Column()
  private BigDecimal logisticsCost;        // Стоимость доставки
  @Column()
  private BigDecimal licenseCost;          // Стоимость лицензий
  @Column()
  private BigDecimal taxCost;              // Налоги
  @Column()
  private BigDecimal socialCost;           // Социальные выплаты
  @Column()
  private BigDecimal amortizationCost;     // Амортизация

  private BigDecimal equipmentMaintenanceCost; // Обслуживание оборудования

  private BigDecimal administrativeCost;   // Административные расходы

  private BigDecimal rentalCost;           // Аренда

  private BigDecimal communicationCost;    // Связь

  private BigDecimal insuranceCost;        // Страхование

  private BigDecimal researchAndDevelopmentCost; // НИОКР

  private BigDecimal interestCost;         // Кредиты и займы, расходы на маркетинг

  private BigDecimal ecoCost;              // Экологический сбор

}
