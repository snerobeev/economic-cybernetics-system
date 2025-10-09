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
import java.util.List;
import java.util.Set;

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

    @Column(name = "name")
    @Size(min = 3, max = 50)
    @NotNull
    private String name; // Название продукта (например, "Смартфон")

    @Column(name = "u_code")
    private String uCode; // Уникальная маркировка: PRD-20251003-001

    @Column(name = "unit")
    @NotNull
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unit; // Единица измерения: шт, тн, м²

    @Column(name = "cost_per_unit")
    private BigDecimal costPerUnit;  // Себестоимость единицы

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit; // Цена реализации (если отличается)

    @Column(name = "producer")
    @Size(min = 3, max = 50)
    private String producer; // Производитель

    @Column(name = "quantity")
    private BigDecimal quantity;  // Объём выпуска

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status; // Статус: PRODUCT, EXPORTED_PRODUCT и т.д.

    @Column(name = "industry_code")
    @Enumerated(EnumType.STRING)
    private IndustryCode industryCode; // Код отрасли (например, ОКВЭД)

    @Column(name = "plan_period")
    private String planPeriod; // Отражает временной интервал

    @Column(name = "production_date")
    @PastOrPresent
    private LocalDate productionDate; // Дата производства

    @Column(name = "strategic")
    @NotNull
    private Boolean strategic; // Является ли продукт стратегическим

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Material> materials;

    @OneToMany
    private Set<Component> components;

    // Конструктор для тестов
    public Product(String name, UnitOfMeasure unit) {
        this.name = name;
        this.unit = unit;

    }
}

