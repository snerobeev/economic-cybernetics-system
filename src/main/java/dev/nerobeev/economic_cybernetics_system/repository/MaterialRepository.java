package dev.nerobeev.economic_cybernetics_system.repository;

import dev.nerobeev.economic_cybernetics_system.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MaterialRepository extends JpaRepository<Material,Long> {
  Optional<Material> findMaterialByName(String name);
  Optional<Material> findMaterialById(Long id);
}
