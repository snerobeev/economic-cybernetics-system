package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Плановый показатель
@Entity
@Table(name = "plan_indicators")
//@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"product"})
public class PlanIndicator {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false)
  private Integer planYear; // Год

  @Column(nullable = false)
  private Integer planQuarter; // Квартал

  @Column(nullable = false, precision = 15, scale = 2)
  private BigDecimal plannedOutput; // Плановый выпуск

  @Column(precision = 15, scale = 2)
  private BigDecimal actualOutput; // Фактический выпуск

  @Column(nullable = false)
  @Builder.Default
  private LocalDateTime createdAt = LocalDateTime.now(); //todo --пока не определено время

  // Конструктор для тестов
  public PlanIndicator(
      Product product, Integer planYear, Integer planQuarter, BigDecimal plannedOutput, BigDecimal actualOutput) {
    this.product = product;
    this.planYear = planYear;
    this.planQuarter = planQuarter;
    this.plannedOutput = plannedOutput;
    this.actualOutput = actualOutput;
  }


}
