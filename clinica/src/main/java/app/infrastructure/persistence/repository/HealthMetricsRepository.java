package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.HealthMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthMetricsRepository extends JpaRepository<HealthMetricsEntity, Long> {
    Optional<HealthMetricsEntity> findByPatientDocument(String patientDocument);
}
