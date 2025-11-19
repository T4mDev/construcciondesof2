package app.adapter.in.validators.billingValidator;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // ✅ Import necesario

/**
 * Validador de datos para la entidad Invoice.
 * Se encarga de verificar que los valores relacionados con una factura
 * cumplan con las reglas de negocio antes de ser aceptados en el sistema.
 */
@Component
public class InvoiceValidator extends SimpleValidator {

    /**
     * Valida el identificador del paciente asociado a la factura.
     * Regla de negocio: debe ser un número válido (no nulo ni vacío).
     * 
     * @param value ID en texto
     * @return ID válido
     * @throws Exception si no cumple con la regla
     */
    public long validatePatientId(String value) throws Exception {
        return longValidator("ID del paciente", value);
    }

    /**
     * Valida el número de póliza asociado a la factura.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value número de póliza
     * @return número válido
     * @throws Exception si no cumple con la regla
     */
    public String validatePolicyNumber(String value) throws Exception {
        return stringValidator("número de póliza", value);
    }

    /**
     * Valida el monto de la factura.
     * Regla de negocio: debe ser un número decimal válido.
     * 
     * @param value monto en texto
     * @return monto válido
     * @throws Exception si no cumple con la regla
     */
    public double validateAmount(String value) throws Exception {
        return doubleValidator("monto de la factura", value);
    }

    /**
     * Valida la descripción de la factura.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value descripción
     * @return descripción válida
     * @throws Exception si no cumple con la regla
     */
    public String validateDescription(String value) throws Exception {
        return stringValidator("descripción de la factura", value);
    }

    /**
     * Valida la fecha y hora de la factura.
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
