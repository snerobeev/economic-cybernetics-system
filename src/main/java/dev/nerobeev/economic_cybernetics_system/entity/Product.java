package dev.nerobeev.economic_cybernetics_system.entity;

import dev.nerobeev.economic_cybernetics_system.domain.cost.model.CostItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
/*
 ** Продукт, к которому привязаны затраты CostItem или <CostItem>
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unit; // единица измерения (тонны, штуки, м³)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PlanIndicator> planIndicators = new ArrayList<>();

//    private List<Material> materials; //todo
//    private List<CostItem> costs; //todo

    // Конструктор для тестов
    public Product(String name, String unit, Sector sector) {
        this.name = name;
        this.unit = unit;
        this.sector = sector;
        this.planIndicators = new ArrayList<>();
    }
}

