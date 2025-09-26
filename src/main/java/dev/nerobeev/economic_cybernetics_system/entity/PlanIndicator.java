package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Плановый показатель
@Entity
@Table(name = "plan_indicators")
@Data
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

  public BigDecimal getDeviation() { // Вычисляет разницу между фактическим и плановым выпуском
    if (actualOutput == null || plannedOutput == null) {
      return null; // todo --нет данных для расчета или период может быть еще не завершен
    }
    return actualOutput.subtract(plannedOutput); // плюс перевыполнение плана, минус недовыполнение
    // todo --возможно понадобится другой тип расчета
  }

  public BigDecimal getDeviationPercent() { // Вычисляет процент отклонения от плана
    BigDecimal deviation = getDeviation();
    if (deviation == null || plannedOutput.compareTo(BigDecimal.ZERO) == 0) { // проверка, избегаем деления на ноль
      return null;
    }
    return deviation.divide(plannedOutput, 4, BigDecimal.ROUND_UP).multiply(BigDecimal.valueOf(100));
    // Формула: (Отклонение / План) × 100%, плюс перевыполнение, минус недовыполнение
  }

  public boolean isCompleted() { // Статус выполнения
    return actualOutput != null; // ← Есть ли фактические данные?
    // true - период завершен, есть фактические данные, false - период в процессе или не начат
  }

/*
--Завершенный план
PlanIndicator completed = PlanIndicator.builder()
                                       .plannedOutput(new BigDecimal("1000"))
                                       .actualOutput(new BigDecimal("1100"))  // ← Факт заполнен
                                        completed.isCompleted(); // → true

--Незавершенный план
PlanIndicator pending = PlanIndicator.builder()
                                      .plannedOutput(new BigDecimal("1000"))
                                      .actualOutput(null)  // ← Факта еще нет
                                       pending.isCompleted(); // → false
*/

  public boolean hasDeviation() { // Есть ли отклонение?
    BigDecimal deviation = getDeviation();
    return deviation != null && deviation.compareTo(BigDecimal.ZERO) != 0;
  }

  public PlanIndicator(
      Product product, Integer planYear, Integer planQuarter, BigDecimal plannedOutput, BigDecimal actualOutput) {
    this.product = product;
    this.planYear = planYear;
    this.planQuarter = planQuarter;
    this.plannedOutput = plannedOutput;
    this.actualOutput = actualOutput;
  }

  /*
deviation != null - есть данные для расчета
deviation.compareTo(BigDecimal.ZERO) != 0 - отклонение не равно
--Точное выполнение плана
actualOutput = 1000, plannedOutput = 1000
hasDeviation() → false  // Отклонения нет

--Перевыполнение
actualOutput = 1100, plannedOutput = 1000
hasDeviation() → true   // Есть отклонение +100

--Недовыполнение
actualOutput = 900, plannedOutput = 1000
hasDeviation() → true   // Есть отклонение -100

--Незавершенный период
actualOutput = null, plannedOutput = 1000
hasDeviation() → false  // Нет данных для расчета

Зачем эти методы:

Анализ эффективности - насколько точно выполняются планы
Система обратной связи - по Ведуте НА, отклонения → корректирующие действия
Отчетность - автоматический расчет KPI
Геймификация - в игровой версии можно давать бонусы за точность
*/

}
