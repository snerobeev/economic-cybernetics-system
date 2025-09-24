package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import lombok.*;

// Продукт/товар
@Entity
@Table(name = "products")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unit; // единица измерения (тонны, штуки, м³)

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private EconomicSector sector;


}

