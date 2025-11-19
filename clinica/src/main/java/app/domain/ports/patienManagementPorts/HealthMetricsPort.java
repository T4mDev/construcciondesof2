package app.domain.ports.patienManagementPorts;

import app.domain.model.patienManagement.HealthMetrics;

import java.util.Optional;

public interface HealthMetricsPort {
    HealthMetrics save(HealthMetrics metrics);

    Optional<HealthMetrics> findById(Long id);

    Optional<HealthMetrics> findByPatientDocument(String patientDocument);

    void delete(Long id);

    boolean isEmpty();
}
