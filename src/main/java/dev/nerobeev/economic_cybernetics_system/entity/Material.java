package dev.nerobeev.economic_cybernetics_system.entity;

import dev.nerobeev.economic_cybernetics_system.domain.newv.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.newv.Status;
import dev.nerobeev.economic_cybernetics_system.domain.newv.UnitOfMeasure;
import jakarta.persistence.*;
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
    public Long id;

    @Column(unique = true)
    @Size(min = 3, max = 50)
    public String name; // Название материала (например, "Сталь")

    @Column(unique = true)
    public String uCode; // Уникальная маркировка: MAT-20251003-001

    @Enumerated(EnumType.STRING)
    public UnitOfMeasure unit; // Единица измерения: шт, тн, м²

    @Column(unique = true)
    public BigDecimal costPerUnit;  // Себестоимость единицы

    @Column(unique = true)
    public BigDecimal pricePerUnit; // Цена реализации (если отличается)

    @Column(unique = true)
    @Size(min = 3, max = 50)
    public String producer; // Производитель

    @Column(unique = true)
    public BigDecimal quantity;  // Объём выпуска

    @Enumerated(EnumType.STRING)
    public Status status; // Статус: PRODUCT, EXPORTED_PRODUCT и т.д.

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    public IndustryCode industryCode; // Код отрасли (например, ОКВЭД)

    @Column(unique = true)
    public String planPeriod; // Отражает временной интервал

    @Column(unique = true)
    @PastOrPresent
    public LocalDate productionDate; // Дата производства

    @Column(unique = true)
    public Boolean strategic; // Является ли продукт стратегическим

    @Column(unique = true)
    public Product product; // В каком продукте используется

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
