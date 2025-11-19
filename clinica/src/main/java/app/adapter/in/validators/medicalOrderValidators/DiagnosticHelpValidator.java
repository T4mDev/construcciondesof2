package app.adapter.in.validators.medicalOrderValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

/**
 * Validador de datos para la entidad DiagnosticHelp.
 * Se encarga de verificar que los valores relacionados con un ítem de ayuda
 * diagnóstica
 * cumplan con las reglas de negocio antes de ser aceptados en el sistema.
 */
@Component
public class DiagnosticHelpValidator extends SimpleValidator {

    /**
     * Valida el identificador del ítem de ayuda diagnóstica.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value ID del ítem
     * @return ID válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateId(String value) throws Exception {
        return stringValidator("ID del ítem de ayuda diagnóstica", value);
    }

    /**
     * Valida el número de ítem.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value número de ítem
     * @return número válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateItemNumber(String value) throws Exception {
        return stringValidator("número de ítem", value);
    }

    /**
     * Valida el nombre del ítem de ayuda diagnóstica.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value nombre del ítem
     * @return nombre válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateName(String value) throws Exception {
        return stringValidator("nombre del ítem", value);
    }

    /**
     * Valida la disponibilidad del resultado del ítem.
     * Regla de negocio: debe ser 'true' o 'false'.
     * 
     * @param value disponibilidad en texto
     * @return valor booleano válido
     * @throws Exception si el valor no es 'true' o 'false'
     */
    public boolean validateResultAvailable(String value) throws Exception {
        return booleanValidator("disponibilidad del resultado", value);
    }
}
