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

    @Column
    @Size(min = 3, max = 50)
    private String name; // Название компонента (например, "SSD диск")

    @Column(unique = true)
    private String uCode; // Уникальная маркировка: CMP-20251003-001

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

    @Column
    @Enumerated(EnumType.STRING)
    private IndustryCode industryCode; // Код отрасли (например, ОКВЭД)

    @Column
    private String planPeriod; // Отражает временной интервал

    @Column
    @PastOrPresent
    private LocalDate productionDate; // Дата производства

    @Column
    private Boolean strategic; // Является ли продукт стратегическим

    @OneToMany
    @JoinTable(name = "component_products",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products; // В каких продуктах используется Компонент

    @OneToMany
    @JoinTable(name = "component_materials",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))

    private Set<Material> materials; // Какие материалы используются в Компоненте

    // Конструктор для тестов
    public Component(String name, String supplier, BigDecimal costPerUnit) {
        this.name = name;
        this.producer = supplier;
        this.costPerUnit = costPerUnit;
    }
}
