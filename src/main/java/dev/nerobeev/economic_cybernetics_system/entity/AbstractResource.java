package dev.nerobeev.economic_cybernetics_system.entity;

import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.measure.UnitOfMeasure;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
public abstract class AbstractResource {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  protected String name; // Название продукта (например, "Смартфон")

  protected String uCode; // Уникальная маркировка: PRD-20251003-001

  @Enumerated(EnumType.STRING)
  protected UnitOfMeasure unit; // Единица измерения: шт, тн, м²

  protected BigDecimal costPerUnit;  // Себестоимость единицы

  protected BigDecimal pricePerUnit; // Цена реализации (если отличается)

  protected String producer; // Производитель

  protected BigDecimal quantity;  // Объём выпуска

  @Enumerated(EnumType.STRING)
  protected Status status; // Статус: PRODUCT, EXPORTED_PRODUCT и т.д.

  protected String industryCode; // Код отрасли (например, ОКВЭД)

  protected String planPeriod; // Отражает временной интервал

  protected LocalDate productionDate; // Дата производства

  protected Boolean strategic; // Является ли продукт стратегическим





}

