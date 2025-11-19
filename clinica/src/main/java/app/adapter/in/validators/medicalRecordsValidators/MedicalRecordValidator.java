package app.adapter.in.validators.medicalRecordsValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;
import app.domain.model.medicalRecords.MedicalRecord;

import java.time.LocalDate;

/**
 * Validador de datos para la entidad MedicalRecord.
 * Se encarga de verificar que los valores relacionados con el historial médico
 * cumplan con las reglas de negocio antes de ser aceptados en el sistema.
 */
@Component
public class MedicalRecordValidator extends SimpleValidator {

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

    /**
     * Valida la fecha de entrada del historial médico.
     * Regla de negocio: la fecha no puede ser futura y debe tener formato válido
     * (yyyy-MM-dd).
     * 
     * @param value fecha en texto
     * @return fecha válida
     * @throws Exception si la fecha es futura o el formato es incorrecto
     */
    public LocalDate validateCreationDate(String value) throws Exception {
        try {
            LocalDate date = LocalDate.parse(value);
            if (date.isAfter(LocalDate.now())) {
                throw new Exception("La fecha de creación no puede ser futura");
            }
            return date;
        } catch (Exception e) {
            throw new Exception("La fecha ingresada no tiene un formato válido (yyyy-MM-dd)");
        }
    }

    /**
     * Valida que la entrada del historial médico no sea nula.
     * Regla de negocio: la entrada debe existir.
     * 
     * @param entry objeto MedicalRecord
     * @throws Exception si la entrada es nula
     */
    public void validateMedicalRecord(MedicalRecord record) throws Exception {
        if (record == null) {
            throw new Exception("La historia clínica no puede ser nula");
        }

        // Validar documento del paciente
        stringValidator("documento del paciente", record.getPatientDocument());

        // Validar detalles generales
        if (record.getGeneralDetails() == null || record.getGeneralDetails().isEmpty()) {
            throw new Exception("Los detalles generales de la historia clínica no pueden estar vacíos");
        }
    }
}
