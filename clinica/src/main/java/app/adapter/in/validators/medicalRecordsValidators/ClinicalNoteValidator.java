package app.adapter.in.validators.medicalRecordsValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Validador de datos para la entidad ClinicalNote.
 * Se encarga de verificar que los valores relacionados con una nota médica
 * cumplan con las reglas de negocio antes de ser aceptados en el sistema.
 */
@Component
public class ClinicalNoteValidator extends SimpleValidator {

    /**
     * Valida el identificador del paciente asociado a la nota médica.
     * Regla de negocio: debe ser un número válido (no nulo ni vacío).
     * 
     * @param value ID del paciente en texto
     * @return ID válido
     * @throws Exception si el valor no cumple con la regla
     */
    public long validatePatientId(String value) throws Exception {
        return longValidator("ID del paciente", value);
    }

    /**
     * Valida el identificador del médico que registra la nota.
     * Regla de negocio: debe ser un número válido (no nulo ni vacío).
     * 
     * @param value ID del médico en texto
     * @return ID válido
     * @throws Exception si el valor no cumple con la regla
     */
    public long validateDoctorId(String value) throws Exception {
        return longValidator("ID del médico", value);
    }

    /**
     * Valida el contenido de la nota médica.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value contenido de la nota
     * @return contenido válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateNoteContent(String value) throws Exception {
        return stringValidator("contenido de la nota médica", value);
    }

    /**
     * Valida la fecha y hora de la nota médica.
     * Regla de negocio: debe tener formato válido (yyyy-MM-dd HH:mm).
     * 
     * @param value fecha y hora en texto
     * @return fecha y hora válidas
     * @throws Exception si el formato es incorrecto
     */
    public LocalDateTime validateDateTime(String value) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(value, formatter);
        } catch (Exception e) {
            throw new Exception("La fecha ingresada no tiene un formato válido (yyyy-MM-dd HH:mm)");
        }
    }
}
