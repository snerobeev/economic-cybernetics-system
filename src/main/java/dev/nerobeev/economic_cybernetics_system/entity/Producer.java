package dev.nerobeev.economic_cybernetics_system.entity;

import dev.nerobeev.economic_cybernetics_system.domain.IndustryCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "companys")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Producer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @NotNull
  private String name;

  @Column(name = "industry_code")
  @Enumerated(EnumType.STRING)
  @NotNull
  private IndustryCode industryCode; // Код отрасли (например, ОКВЭД)

  private Long costOfProducer;

  private Long costPerUnit;          // Себестоимость единицы

}
