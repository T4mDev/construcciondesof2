package app.application.usecases.patientManagementUseCases;

import app.domain.model.patienManagement.HealthMetrics;
import app.domain.services.patientManagementServices.HealthMetricsService;

import org.springframework.stereotype.Component;

@Component
public class HealthMetricsUseCase {

    private final HealthMetricsService healthMetricsService;

    public HealthMetricsUseCase(HealthMetricsService healthMetricsService) {
        this.healthMetricsService = healthMetricsService;
    }

    public void createHealthMetrics(HealthMetrics metrics, String patientDocument) throws Exception {
        healthMetricsService.create(metrics, patientDocument);
    }

    public HealthMetrics findHealthMetricsByPatient(String patientDocument) throws Exception {
        return healthMetricsService.findByPatient(patientDocument);
    }

    public void updateHealthMetrics(HealthMetrics metrics, String patientDocument) throws Exception {
        healthMetricsService.update(metrics, patientDocument);
    }

    public void deleteHealthMetrics(String patientDocument) throws Exception {
        healthMetricsService.delete(patientDocument);
    }

    public void validateHealthMetrics(HealthMetrics metrics) throws Exception {
        healthMetricsService.validateMetrics(metrics);
    }
}
