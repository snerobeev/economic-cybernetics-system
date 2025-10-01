package dev.nerobeev.economic_cybernetics_system.entity;

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
    private BigDecimal cost;

    // Конструктор для тестов
    public Material(String name, String supplier, BigDecimal cost) {
        this.name = name;
        this.supplier = supplier;
        this.cost = cost;
    }

    /*
     *
     *  Можно добавить поле quantity и unit, учитывать объём.
     *  Можно связать Material с Product через @ManyToOne или @ManyToMany, в зависимости от модели.
     *  Можно добавить @CreatedDate и @LastModifiedDate для аудита.
     */


}
