package dev.nerobeev.economic_cybernetics_system.entity;

import dev.nerobeev.economic_cybernetics_system.domain.newv.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.newv.Status;
import dev.nerobeev.economic_cybernetics_system.domain.newv.measure.UnitOfMeasure;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
@Setter
public abstract class AbstractResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true)
    @Size(min = 3, max = 50)
    protected String name; // Название продукта (например, "Смартфон")

    @Column(unique = true)
    public String uCode; // Уникальная маркировка: PRD-20251003-001

    @Enumerated(EnumType.STRING)
    protected UnitOfMeasure unit; // Единица измерения: шт, тн, м²

    @Column(unique = true)
    protected BigDecimal costPerUnit;  // Себестоимость единицы

    @Column(unique = true)
    protected BigDecimal pricePerUnit; // Цена реализации (если отличается)

    @Column(unique = true)
    @Size(min = 3, max = 50)
    protected String producer; // Производитель

    @Column(unique = true)
    protected BigDecimal quantity;  // Объём выпуска

    @Enumerated(EnumType.STRING)
    protected Status status; // Статус: PRODUCT, EXPORTED_PRODUCT и т.д.

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    protected IndustryCode industryCode; // Код отрасли (например, ОКВЭД)

    @Column(unique = true)
    protected String planPeriod; // Отражает временной интервал


    @Column(unique = true)
    @PastOrPresent
    protected LocalDate productionDate; // Дата производства

    @Column(unique = true)
    protected Boolean strategic; // Является ли продукт стратегическим


}

