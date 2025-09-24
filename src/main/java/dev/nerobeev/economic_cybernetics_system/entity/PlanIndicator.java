package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Плановый показатель
@Entity
@Table(name = "plan_indicators")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
 public class PlanIndicator {
    //todo equals&hashcode
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer planYear; // Годовой план

    @Column(nullable = false)
    private Integer planQuarter; // Квартальный план

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal plannedOutput; // Плановый выпуск

    @Column(precision = 15, scale = 2)
    private BigDecimal actualOutput; // Фактический выпуск

    @Column(nullable = false)
    private LocalDateTime createdAt; //todo

}
