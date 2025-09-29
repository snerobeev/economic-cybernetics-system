package dev.nerobeev.economic_cybernetics_system.repository;

import dev.nerobeev.economic_cybernetics_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
  Optional<Product> findById(Long id);
}
