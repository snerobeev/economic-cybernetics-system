package dev.nerobeev.economic_cybernetics_system.entity;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import dev.nerobeev.economic_cybernetics_system.domain.Status;
import dev.nerobeev.economic_cybernetics_system.domain.UnitOfMeasure;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "components")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Component {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column
  @Size(min = 3, max = 50)
  private String name; // Название компонента (например, "SSD диск")

  @NotNull
  @Column(name = "ucode")
  private String uCode; // Уникальная маркировка: CMP-20251003-001

  @Column(name = "unit")
  @Enumerated(EnumType.STRING)
  @NotNull
  private UnitOfMeasure unit; // Единица измерения: шт, тн, м²

  @NotNull
  @Column(name = "cost_per_unit")
  private Long costPerUnit;  // Себестоимость единицы

  @NotNull
  @Column(name = "price_rep_unit")
  private Long pricePerUnit; // Цена реализации (если отличается)

  @NotNull
  @Column(name = "producer")
  @Size(min = 3, max = 50)
  private String producer; // Производитель

  @Column(name = "quantity")
  private Long quantity;  // Объём выпуска

  @NotNull
  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private Status status; // Статус: PRODUCT, EXPORTED_PRODUCT и т.д.

  @Column(name = "industry_code")
  @Enumerated(EnumType.STRING)
  private IndustryCode industryCode; // Код отрасли (например, ОКВЭД)

  @Column(name = "plan_period")
  private String planPeriod; // Отражает временной интервал

  @Column
  @PastOrPresent
  private LocalDate productionDate; // Дата производства

  @Column
  private Boolean strategic; // Является ли продукт стратегическим

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "component_materials",
      joinColumns = @JoinColumn(name = "component_id"),
      inverseJoinColumns = @JoinColumn(name = "material_id"))
  private Set<Material> materials; // Какие материалы используются в Компоненте

  // Конструктор для тестов
  public Component(String name, String supplier, Long costPerUnit) {
    this.name = name;
    this.producer = supplier;
    this.costPerUnit = costPerUnit;
  }
}
