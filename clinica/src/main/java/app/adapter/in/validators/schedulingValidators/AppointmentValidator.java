package app.adapter.in.validators.schedulingValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Validador de datos para la entidad Appointment.
 * Se encarga de verificar que los valores relacionados con una cita médica
 * cumplan con las reglas de negocio antes de ser aceptados en el sistema.
 */
@Component
public class AppointmentValidator extends SimpleValidator {

    /**
     * Valida la fecha y hora de la cita.
     * Regla de negocio: debe tener formato válido (yyyy-MM-dd HH:mm) y ser futura.
     * 
     * @param value fecha y hora en texto
     * @return fecha y hora válidas
     * @throws Exception si el formato es incorrecto o la fecha es pasada
     */
    public LocalDateTime validateDateTime(String value) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(value.trim(), formatter);
            if (dateTime.isBefore(LocalDateTime.now())) {
                throw new Exception("La fecha de la cita debe ser futura");
            }
            return dateTime;
        } catch (Exception e) {
            throw new Exception("La fecha ingresada no tiene un formato válido (yyyy-MM-dd HH:mm)");
        }
    }

    /**
     * Valida el motivo de la cita.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value motivo de la cita
     * @return motivo válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateReason(String value) throws Exception {
        return stringValidator("motivo de la cita", value);
    }

    /**
     * Valida el identificador del paciente asociado a la cita.
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
     * Valida el identificador del doctor asociado a la cita.
     * Regla de negocio: debe ser un número válido (no nulo ni vacío).
     * 
     * @param value ID del doctor en texto
     * @return ID válido
     * @throws Exception si el valor no cumple con la regla
     */
    public long validateDoctorId(String value) throws Exception {
        return longValidator("ID del doctor", value);
    }
}
