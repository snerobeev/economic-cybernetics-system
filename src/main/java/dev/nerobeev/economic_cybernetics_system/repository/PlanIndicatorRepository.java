package dev.nerobeev.economic_cybernetics_system.repository;

import dev.nerobeev.economic_cybernetics_system.entity.PlanIndicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanIndicatorRepository extends JpaRepository<PlanIndicator,Long> {
}
