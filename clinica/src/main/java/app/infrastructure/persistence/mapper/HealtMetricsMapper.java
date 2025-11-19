package app.infrastructure.persistence.mapper;

import app.domain.model.patienManagement.HealthMetrics;
import app.infrastructure.persistence.entities.HealthMetricsEntity;

public class HealtMetricsMapper {

    public static HealthMetricsEntity toEntity(HealthMetrics healthMetrics) {
        if (healthMetrics == null)
            return null;
        HealthMetricsEntity entity = new HealthMetricsEntity();
        entity.setId(healthMetrics.getId());
        entity.setPatientDocument(healthMetrics.getPatientDocument());
        entity.setBloodPressure(healthMetrics.getBloodPressure());
        entity.setTemperature(healthMetrics.getTemperature());
        entity.setPulse(healthMetrics.getPulse());
        entity.setOxygenLevel(healthMetrics.getOxygenLevel());
        entity.setHealthMetricsEntityDetails(healthMetrics.getHealthMetricsDetails());
        return entity;
    }

    public static HealthMetrics toDomain(HealthMetricsEntity entity) {
        if (entity == null)
            return null;
        HealthMetrics healthMetrics = new HealthMetrics();
        healthMetrics.setId(entity.getId());
        healthMetrics.setPatientDocument(entity.getPatientDocument());
        healthMetrics.setBloodPressure(entity.getBloodPressure());
        healthMetrics.setTemperature(entity.getTemperature());
        healthMetrics.setPulse(entity.getPulse());
        healthMetrics.setOxygenLevel(entity.getOxygenLevel());
        healthMetrics.setHealthMetricsDetails(entity.getHealthMetricsEntityDetails());
        return healthMetrics;
    }
}
