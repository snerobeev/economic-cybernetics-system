package dev.nerobeev.economic_cybernetics_system.entity;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
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
    private Long id;

    @Column
    @Size(min = 3, max = 50)
    private String name; // Название продукта (например, "Смартфон")

    @Column(unique = true)
    private String uCode; // Уникальная маркировка: PRD-20251003-001

    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unit; // Единица измерения: шт, тн, м²

    @Column
    private BigDecimal costPerUnit;  // Себестоимость единицы

    @Column
    private BigDecimal pricePerUnit; // Цена реализации (если отличается)

    @Column
    @Size(min = 3, max = 50)
    private String producer; // Производитель

    @Column
    private BigDecimal quantity;  // Объём выпуска

    @Enumerated(EnumType.STRING)
    private Status status; // Статус: PRODUCT, EXPORTED_PRODUCT и т.д.

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private IndustryCode industryCode; // Код отрасли (например, ОКВЭД)

    @Column
    private String planPeriod; // Отражает временной интервал

    @Column
    @PastOrPresent
    private LocalDate productionDate; // Дата производства

    @Column
    private Boolean strategic; // Является ли продукт стратегическим

    @OneToMany()
    private List<Material> materials;

    @OneToMany
    private List<Component> components;

    // Конструктор для тестов
    public Product(String name, UnitOfMeasure unit) {
        this.name = name;
        this.unit = unit;

    }
}

