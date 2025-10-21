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
    @NotNull
    private String name; // Название материала (например, "Сталь")

    @Column(name = "ucode")
    private String uCode; // Уникальная маркировка: MAT-20251003-001

    @Column(name = "unit")
    @Enumerated(EnumType.STRING)
    @NotNull
    private UnitOfMeasure unit; // Единица измерения: шт, тн, м²

    @Column(name = "cost_per_unit")
    @NotNull
    private Long costPerUnit;  // Себестоимость единицы

    @Column(name = "price_per_unit")
    @NotNull
    private Long pricePerUnit; // Цена реализации (если отличается)

    @Column(name = "producer")
    @Size(min = 3, max = 50)
    @NotNull
    private String producer; // Производитель

    @Column(name = "quantity")
    @NotNull
    private Long quantity;  // Объём выпуска

    @Column()
    @Enumerated(EnumType.STRING)
    private Status status; // Статус: PRODUCT, EXPORTED_PRODUCT и т.д.

    @Column(name = "industry_code")
    @Enumerated(EnumType.STRING)
    @NotNull
    private IndustryCode industryCode; // Код отрасли (например, ОКВЭД)

    @Column(name = "plan_period")
    private String planPeriod; // Отражает временной интервал

    @Column(name = "production_date")
    @PastOrPresent
    private LocalDate productionDate; // Дата производства

    @Column(name = "strategic")
    @NotNull
    private Boolean strategic; // Является ли продукт стратегическим

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product; //todo

    // Конструктор для тестов
    public Material(String name, String producer, Long costPerUnit) {
        this.name = name;
        this.producer = producer;
        this.costPerUnit = costPerUnit;
    }

    /*
     *  Можно связать Material с Product через @ManyToOne или @ManyToMany, в зависимости от модели.
     *  Можно добавить @CreatedDate и @LastModifiedDate для аудита.
     */

}
