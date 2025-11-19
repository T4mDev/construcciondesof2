package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.DiagnosticHelpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosticHelpRepository extends JpaRepository<DiagnosticHelpEntity, Long> {
    Optional<DiagnosticHelpEntity> findByItemNumber(String itemNumber);
}
