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

    @Column
    @Size(min = 3, max = 50)
    public String name; // Название материала (например, "Сталь")

    @Column(unique = true)
    public String uCode; // Уникальная маркировка: MAT-20251003-001

    @Enumerated(EnumType.STRING)
    public UnitOfMeasure unit; // Единица измерения: шт, тн, м²

    @Column
    public BigDecimal costPerUnit;  // Себестоимость единицы

    @Column
    public BigDecimal pricePerUnit; // Цена реализации (если отличается)

    @Column
    @Size(min = 3, max = 50)
    public String producer; // Производитель

    @Column
    public BigDecimal quantity;  // Объём выпуска

    @Enumerated(EnumType.STRING)
    public Status status; // Статус: PRODUCT, EXPORTED_PRODUCT и т.д.

    @Column
    @Enumerated(EnumType.STRING)
    public IndustryCode industryCode; // Код отрасли (например, ОКВЭД)

    @Column
    public String planPeriod; // Отражает временной интервал

    @Column
    @PastOrPresent
    public LocalDate productionDate; // Дата производства

    @Column
    public Boolean strategic; // Является ли продукт стратегическим

    @ManyToOne
    @JoinColumn(name = "product_id")
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
