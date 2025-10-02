package dev.nerobeev.economic_cybernetics_system.entity;

import dev.nerobeev.economic_cybernetics_system.domain.measure.UnitOfMeasure;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;

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
    private String name;

    private String supplier;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private BigDecimal costPerUnit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitOfMeasure unit;

    // Конструктор для тестов
    public Material(String name, String supplier, BigDecimal costPerUnit) {
        this.name = name;
        this.supplier = supplier;
        this.costPerUnit = costPerUnit;
    }

    /*
     *
     *  Можно добавить поле quantity и unit, учитывать объём.
     *  Можно связать Material с Product через @ManyToOne или @ManyToMany, в зависимости от модели.
     *  Можно добавить @CreatedDate и @LastModifiedDate для аудита.
     */

}
