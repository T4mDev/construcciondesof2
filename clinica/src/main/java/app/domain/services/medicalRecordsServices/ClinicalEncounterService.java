package app.domain.services.medicalRecordsServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.medicalOrders.MedicalOrder;
import app.domain.model.medicalRecords.ClinicalEncounter;
import app.domain.model.patienManagement.HealthMetrics;
import app.domain.ports.medicalRecordsPorts.ClinicalEncounterPort;

/**
 * Servicio de dominio para la gestión de encuentros clínicos.
 * Encapsula las reglas de negocio relacionadas con la creación, búsqueda,
 * actualización de signos vitales y asociación de órdenes médicas,
 * delegando la persistencia en {@link ClinicalEncounterPort}.
 */
@Service
public class ClinicalEncounterService {

    @Autowired
    private final ClinicalEncounterPort clinicalEncounterPort;

    /**
     * Constructor que inyecta el puerto de persistencia de encuentros clínicos.
     * 
     * @param clinicalEncounterPort instancia de {@link ClinicalEncounterPort}
     */
    public ClinicalEncounterService(ClinicalEncounterPort clinicalEncounterPort) {
        this.clinicalEncounterPort = clinicalEncounterPort;
    }

    /**
     * Crea un nuevo encuentro clínico para un paciente.
     * Reglas de negocio:
     * <ul>
     * <li>No puede existir más de un encuentro clínico para el mismo paciente.</li>
     * </ul>
     * 
     * @param encounter       objeto {@link ClinicalEncounter} a crear
     * @param patientDocument documento del paciente
     * @throws Exception si ya existe un encuentro clínico para el paciente
     */
    public void create(ClinicalEncounter encounter, String patientDocument) throws Exception {
        ClinicalEncounter existing = clinicalEncounterPort.findByPatientId(patientDocument);
        if (existing != null) {
            throw new Exception("Ya existe un encuentro clínico para este paciente");
        }
        clinicalEncounterPort.save(encounter);
    }

    /**
     * Busca un encuentro clínico por documento del paciente.
     * Regla de negocio: el encuentro clínico debe existir en el sistema.
     * 
     * @param patientDocument documento del paciente
     * @return encuentro clínico encontrado
     * @throws Exception si no se encuentra el encuentro clínico
     */
    public ClinicalEncounter findByPatient(String patientDocument) throws Exception {
        ClinicalEncounter encounter = clinicalEncounterPort.findByPatientId(patientDocument);
        if (encounter == null) {
            throw new Exception("No se encontró el encuentro clínico del paciente");
        }
        return encounter;
    }

    /**
     * Actualiza los signos vitales de un encuentro clínico.
     * Reglas de negocio:
     * <ul>
     * <li>El encuentro clínico debe existir.</li>
     * <li>Los signos vitales no pueden ser nulos.</li>
     * </ul>
     * 
     * @param patientDocument documento del paciente
     * @param signs           objeto {@link HealthMetrics} con los signos vitales
     * @throws Exception si no se encuentra el encuentro clínico
     */
    public void updateHealthMetrics(String patientDocument, HealthMetrics signs) throws Exception {
        ClinicalEncounter encounter = clinicalEncounterPort.findByPatientId(patientDocument);
        if (encounter == null) {
            throw new Exception("No se encontró el encuentro clínico para actualizar signos vitales");
        }
        encounter.setHealthMetrics(signs);
        clinicalEncounterPort.save(encounter);
    }

    /**
     * Adjunta una orden médica a un encuentro clínico.
     * Reglas de negocio:
     * <ul>
     * <li>El encuentro clínico debe existir.</li>
     * <li>La orden médica no puede ser nula.</li>
     * </ul>
     * 
     * @param patientDocument documento del paciente
     * @param order           objeto {@link MedicalOrder} a adjuntar
     * @throws Exception si no se encuentra el encuentro clínico
     */
    public void attachOrder(String patientDocument, MedicalOrder order) throws Exception {
        ClinicalEncounter encounter = clinicalEncounterPort.findByPatientId(patientDocument);
        if (encounter == null) {
            throw new Exception("No se encontró el encuentro clínico para adjuntar la orden");
        }
        encounter.setMedicalOrder(order);
        clinicalEncounterPort.save(encounter);
    }
}
