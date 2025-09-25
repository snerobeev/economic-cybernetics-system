package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// Отрасль экономики
@Entity
@Table(name = "sectors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"products"})
public class EconomicSector {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false, unique = true) //Уникальность по коду - сектора различаются по коду ("01", "02", etc.)
  @EqualsAndHashCode.Include
  private String code; // Код отрасли (например, "01" - сельское хозяйство)

  private String description;

  // Связь с продуктами
  @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<Product> products = new ArrayList<>();

  // Конструктор для тестов
  public EconomicSector(String name, String code, String description) {
    this.name = name;
    this.code = code;
    this.description = description;
    this.products = new ArrayList<>();
  }
}



