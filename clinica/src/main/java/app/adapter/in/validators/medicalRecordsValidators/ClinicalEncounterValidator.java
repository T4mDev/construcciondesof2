package app.adapter.in.validators.medicalRecordsValidators;

import app.adapter.in.validators.SimpleValidator;
import app.domain.model.medicalOrders.MedicalOrder;
import app.domain.model.medicalRecords.ClinicalNote;
import app.domain.model.patienManagement.HealthMetrics;
import app.domain.model.patienManagement.Patient;

import org.springframework.stereotype.Component;

/**
 * Validador de datos para la entidad ClinicalEncounter.
 * Se encarga de verificar que los elementos principales de un encuentro clínico
 * (paciente, nota médica, orden clínica, signos vitales y documento del
 * paciente)
 * cumplan con las reglas de negocio antes de ser aceptados en el sistema.
 */
@Component
public class ClinicalEncounterValidator extends SimpleValidator {

    /**
     * Valida que el paciente no sea nulo.
     * Regla de negocio: el encuentro clínico debe tener un paciente asociado.
     * 
     * @param patient objeto Patient
     * @throws Exception si el paciente es nulo
     */
    public void validatePatient(Patient patient) throws Exception {
        if (patient == null) {
            throw new Exception("El paciente no puede ser nulo");
        }
    }

    /**
     * Valida que la nota médica no sea nula.
     * Regla de negocio: el encuentro clínico debe tener una nota médica registrada.
     * 
     * @param note objeto ClinicalNote
     * @throws Exception si la nota es nula
     */
    public void validateClinicalNote(ClinicalNote note) throws Exception {
        if (note == null) {
            throw new Exception("La nota médica no puede ser nula");
        }
    }

    /**
     * Valida que la orden clínica no sea nula.
     * Regla de negocio: el encuentro clínico debe tener una orden clínica asociada.
     * 
     * @param order objeto MedicalOrder
     * @throws Exception si la orden es nula
     */
    public void validateMedicalOrder(MedicalOrder order) throws Exception {
        if (order == null) {
            throw new Exception("La orden clínica no puede ser nula");
        }
    }

    /**
     * Valida que los signos vitales no sean nulos.
     * Regla de negocio: el encuentro clínico debe registrar signos vitales.
     * 
     * @param signs objeto HealthMetrics
     * @throws Exception si los signos vitales son nulos
     */
    public void validateHealthMetrics(HealthMetrics signs) throws Exception {
        if (signs == null) {
            throw new Exception("Los signos vitales no pueden ser nulos");
        }
    }

    /**
     * Valida el documento del paciente.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value documento del paciente
     * @return documento válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validatePatientDocument(String value) throws Exception {
        return stringValidator("documento del paciente", value);
    }
}
