package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// Продукт/товар
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString(exclude = {"planIndicators", "sector"})
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
  private EconomicSector sector;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<PlanIndicator> planIndicators = new ArrayList<>();

  // Конструктор для тестов
  public Product(String name, String unit, EconomicSector sector) {
    this.name = name;
    this.unit = unit;
    this.sector = sector;
    this.planIndicators = new ArrayList<>();
  }
}

