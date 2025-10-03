package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * JPA-сущность, представляющая материальный ресурс,
 * используемый в производственном процессе.
 */

@Entity
@Table(name = "materials")
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Material extends AbstractResource {

    // Конструктор для тестов
    public Material(String name, String supplier, BigDecimal costPerUnit) {
        this.name = name;
        this.producer = supplier;
        this.costPerUnit = costPerUnit;
    }

    /*
     *  Можно связать Material с Product через @ManyToOne или @ManyToMany, в зависимости от модели.
     *  Можно добавить @CreatedDate и @LastModifiedDate для аудита.
     */

}
