package app.adapter.out.persistence.patientManagementAdapters;

import app.domain.model.patienManagement.HealthMetrics;
import app.domain.ports.patienManagementPorts.HealthMetricsPort;
import app.infrastructure.persistence.mapper.HealtMetricsMapper;
import app.infrastructure.persistence.repository.HealthMetricsRepository;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class HealthMetricsAdapter implements HealthMetricsPort {

    private final HealthMetricsRepository repository;

    public HealthMetricsAdapter(HealthMetricsRepository repository) {
        this.repository = repository;
    }

    @Override
    public HealthMetrics save(HealthMetrics metrics) {
        var entity = HealtMetricsMapper.toEntity(metrics);
        var saved = repository.save(entity);
        return HealtMetricsMapper.toDomain(saved);
    }

    @Override
    public Optional<HealthMetrics> findById(Long id) {
        return repository.findById(id).map(HealtMetricsMapper::toDomain);
    }

    @Override
    public Optional<HealthMetrics> findByPatientDocument(String patientDocument) {
        return repository.findByPatientDocument(patientDocument).map(HealtMetricsMapper::toDomain);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }
}
