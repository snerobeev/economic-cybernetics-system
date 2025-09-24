package dev.nerobeev.economic_cybernetics_system.entity;

import jakarta.persistence.*;
import lombok.*;

// Отрасль экономики
@Entity
@Table(name = "sectors")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class EconomicSector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String code; // Код отрасли (например, "01" - сельское хозяйство)

    private String description;


}



