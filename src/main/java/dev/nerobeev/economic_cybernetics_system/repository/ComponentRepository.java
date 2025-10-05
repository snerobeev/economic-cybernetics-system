package dev.nerobeev.economic_cybernetics_system.repository;

import dev.nerobeev.economic_cybernetics_system.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
    Optional<Component> findById(Long id);
}
