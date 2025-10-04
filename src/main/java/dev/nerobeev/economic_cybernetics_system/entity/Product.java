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
import java.util.List;

/*
 ** JPA-сущность, представляющая готовый продукт,
 *  итог производственного процесса.
 */
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true)
    @Size(min = 3, max = 50)
    public String name; // Название продукта (например, "Смартфон")

    @Column(unique = true)
    public String uCode; // Уникальная маркировка: PRD-20251003-001

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

    @OneToMany()
    private List<Material> materials;


    // Конструктор для тестов
    public Product(String name, UnitOfMeasure unit) {
        this.name = name;
        this.unit = unit;

    }
}

