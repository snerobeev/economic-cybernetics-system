package dev.nerobeev.economic_cybernetics_system.entity;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * JPA-сущность, представляющая материальный ресурс,
 * используемый в производственном процессе.
 */
@Entity
@Table(name = "materials")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String name; // Название материала (например, "Сталь")

    @Column(unique = true)
    private String uCode; // Уникальная маркировка: MAT-20251003-001

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unit; // Единица измерения: шт, тн, м²

    @Column(nullable = false)
    private BigDecimal costPerUnit;  // Себестоимость единицы

    @Column(nullable = false)
    private BigDecimal pricePerUnit; // Цена реализации (если отличается)

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String producer; // Производитель

    @Column(nullable = false)
    private BigDecimal quantity;  // Объём выпуска

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status; // Статус: PRODUCT, EXPORTED_PRODUCT и т.д.

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IndustryCode industryCode; // Код отрасли (например, ОКВЭД)

    @Column(name = "plan_period")
    private String planPeriod; // Отражает временной интервал

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate productionDate; // Дата производства

    @Column(nullable = false)
    @NotNull
    private Boolean strategic; // Является ли продукт стратегическим

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Конструктор для тестов
    public Material(String name, String supplier, BigDecimal costPerUnit) {
        this.name = name;
        this.producer = supplier;
        this.costPerUnit = costPerUnit;
    }

    /*
     *  Можно связать Material с Product через @ManyToOne или @ManyToMany, в зависимости от модели.
     *  Можно добавить @CreatedDate и @LastModifiedDate для аудита.
     */

}
