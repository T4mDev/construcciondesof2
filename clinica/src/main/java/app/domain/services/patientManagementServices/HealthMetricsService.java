package app.domain.services.patientManagementServices;

import app.domain.model.patienManagement.HealthMetrics;
import app.domain.ports.patienManagementPorts.HealthMetricsPort;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * Servicio de dominio para la gestión de métricas de salud.
 * Encapsula las reglas de negocio relacionadas con la creación, búsqueda,
 * actualización, eliminación y validación de métricas de salud de un paciente,
 * delegando la persistencia en {@link HealthMetricsPort}.
 */
@Service
public class HealthMetricsService {

    private final HealthMetricsPort healthMetricsPort;

    /**
     * Constructor que inyecta el puerto de persistencia de métricas de salud.
     * 
     * @param healthMetricsPort instancia de {@link HealthMetricsPort}
     */
    public HealthMetricsService(HealthMetricsPort healthMetricsPort) {
        this.healthMetricsPort = healthMetricsPort;
    }

    /**
     * Crea nuevas métricas de salud para un paciente.
     * Reglas de negocio:
     * <ul>
     * <li>Un paciente no puede tener más de un registro de métricas de salud.</li>
     * <li>Las métricas deben estar dentro de rangos clínicos válidos.</li>
     * </ul>
     * 
     * @param metrics         objeto {@link HealthMetrics} a crear
     * @param patientDocument documento del paciente
     * @throws Exception si ya existen métricas registradas o si las métricas son
     *                   inválidas
     */
    public void create(HealthMetrics metrics, String patientDocument) throws Exception {
        Optional<HealthMetrics> existingOpt = healthMetricsPort.findByPatientDocument(patientDocument);
        if (existingOpt.isPresent()) {
            throw new Exception("Ya existen métricas de salud registradas para este paciente");
        }

        validateMetrics(metrics);
        healthMetricsPort.save(metrics);
    }

    /**
     * Busca métricas de salud por documento del paciente.
     * Regla de negocio: las métricas deben existir en el sistema.
     * 
     * @param patientDocument documento del paciente
     * @return métricas de salud encontradas
     * @throws Exception si no se encuentran métricas
     */
    public HealthMetrics findByPatient(String patientDocument) throws Exception {
        Optional<HealthMetrics> metricsOpt = healthMetricsPort.findByPatientDocument(patientDocument);
        if (metricsOpt.isEmpty()) {
            throw new Exception("No se encontraron métricas de salud para el paciente");
        }
        return metricsOpt.get();
    }

    /**
     * Actualiza métricas de salud existentes.
     * Reglas de negocio:
     * <ul>
     * <li>Las métricas deben existir en el sistema.</li>
     * <li>Las métricas deben estar dentro de rangos clínicos válidos.</li>
     * </ul>
     * 
     * @param metrics         objeto {@link HealthMetrics} con los nuevos valores
     * @param patientDocument documento del paciente
     * @throws Exception si las métricas no existen o son inválidas
     */
    public void update(HealthMetrics metrics, String patientDocument) throws Exception {
        Optional<HealthMetrics> existingOpt = healthMetricsPort.findByPatientDocument(patientDocument);
        if (existingOpt.isEmpty()) {
            throw new Exception("No se encontraron métricas de salud para actualizar");
        }

        validateMetrics(metrics);
        healthMetricsPort.save(metrics);
    }

    /**
     * Elimina métricas de salud existentes.
     * Regla de negocio: las métricas deben existir en el sistema.
     * 
     * @param patientDocument documento del paciente
     * @throws Exception si no se encuentran métricas
     */
    public void delete(String patientDocument) throws Exception {
        Optional<HealthMetrics> existingOpt = healthMetricsPort.findByPatientDocument(patientDocument);
        if (existingOpt.isEmpty()) {
            throw new Exception("No se encontraron métricas de salud para eliminar");
        }

        HealthMetrics existing = existingOpt.get();
        if (existing.getId() == null) {
            throw new Exception("Métricas sin identificador: imposible eliminar");
        }

        healthMetricsPort.delete(existing.getId());
    }

    /**
     * Valida que las métricas de salud estén dentro de rangos clínicos válidos.
     * Reglas de negocio:
     * <ul>
     * <li>Presión arterial: entre 1 y 300.</li>
     * <li>Temperatura: entre 30°C y 45°C.</li>
     * <li>Pulso: entre 1 y 220.</li>
     * <li>Nivel de oxígeno: entre 50% y 100%.</li>
     * </ul>
     * 
     * @param metrics objeto {@link HealthMetrics} a validar
     * @throws Exception si alguna métrica está fuera de rango
     */
    public void validateMetrics(HealthMetrics metrics) throws Exception {
        if (metrics.getBloodPressure() == null || metrics.getBloodPressure().isEmpty()) {
            throw new Exception("La presión arterial debe estar en un rango clínico válido");
        }

        if (metrics.getTemperature() < 30 || metrics.getTemperature() > 45) {
            throw new Exception("La temperatura debe estar entre 30°C y 45°C");
        }

        if (metrics.getPulse() <= 0 || metrics.getPulse() > 220) {
            throw new Exception("El pulso debe estar en un rango clínico válido");
        }

        if (metrics.getHeartRate() <= 0 || metrics.getHeartRate() > 220) {
            throw new Exception("El ritmo cardiaco debe estar en un rango clínico válido");
        }

        if (metrics.getOxygenLevel() < 50 || metrics.getOxygenLevel() > 100) {
            throw new Exception("El nivel de oxígeno debe estar entre 50% y 100%");
        }

        if (metrics.getWeight() <= 0 || metrics.getWeight() > 400) {
            throw new Exception("El peso debe estar entre un rango clínico válido");
        }
    }
}
