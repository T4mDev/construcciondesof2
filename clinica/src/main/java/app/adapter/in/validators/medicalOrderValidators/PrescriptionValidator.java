package app.adapter.in.validators.medicalOrderValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

/**
 * Validador de datos para la entidad Prescription.
 * Se encarga de verificar que los valores relacionados con un ítem de
 * medicamento
 * cumplan con las reglas básicas de negocio antes de ser aceptados en el
 * sistema.
 */
@Component
public class PrescriptionValidator extends SimpleValidator {

    /**
     * Valida el identificador del ítem de medicamento.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value ID en texto
     * @return ID válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateId(String value) throws Exception {
        return stringValidator("ID del ítem de medicamento", value);
    }

    /**
     * Valida la dosis del medicamento.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value dosis en texto
     * @return dosis válida
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateDose(String value) throws Exception {
        return stringValidator("dosis del medicamento", value);
    }

    /**
     * Valida la duración del tratamiento en días.
     * Regla de negocio: debe ser un número entero mayor a cero.
     * 
     * @param value duración en texto
     * @return número de días válido
     * @throws Exception si el valor no es numérico o es menor/igual a cero
     */
    public int validateDurationDays(String value) throws Exception {
        int days = integerValidator("duración del tratamiento", value);
        if (days <= 0) {
            throw new Exception("La duración del tratamiento debe ser mayor a cero");
        }
        return days;
    }

    /**
     * Valida el codigo de ítem.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value codigo de ítem
     * @return codigo válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateItemCode(String value) throws Exception {
        return stringValidator("codigo del ítem", value);
    }
}
