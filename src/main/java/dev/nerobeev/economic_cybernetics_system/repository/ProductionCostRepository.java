package dev.nerobeev.economic_cybernetics_system.repository;

import dev.nerobeev.economic_cybernetics_system.entity.ProductionCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductionCostRepository extends JpaRepository<ProductionCost,Long> {
  Optional<ProductionCost>findProductionCostById(Long id);
}
