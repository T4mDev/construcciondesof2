package app.adapter.in.validators.medicalOrderValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

/**
 * Validador de datos para los detalles de una orden de procedimiento.
 * Se encarga de verificar que los valores ingresados cumplan con las reglas
 * básicas de negocio antes de ser aceptados en el sistema.
 */
@Component
public class ProcedureOrderDetailValidator extends SimpleValidator {

    /**
     * Valida el identificador del ítem de procedimiento.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value ID en texto
     * @return ID válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateId(String value) throws Exception {
        return stringValidator("ID del ítem de procedimiento", value);
    }

    /**
     * Valida la cantidad del procedimiento.
     * Regla de negocio: debe ser un número entero mayor a cero.
     * 
     * @param value cantidad en texto
     * @return cantidad válida
     * @throws Exception si la cantidad es menor o igual a cero o no es numérica
     */
    public int validateQuantity(String value) throws Exception {
        int quantity = integerValidator("cantidad del procedimiento", value);
        if (quantity <= 0) {
            throw new Exception("La cantidad debe ser mayor a cero");
        }
        return quantity;
    }

    /**
     * Valida la frecuencia del procedimiento.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value frecuencia en texto
     * @return frecuencia válida
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateFrequency(String value) throws Exception {
        return stringValidator("frecuencia del procedimiento", value);
    }

    /**
     * Valida si el procedimiento requiere especialista.
     * Regla de negocio: el valor debe ser 'true' o 'false'.
     * 
     * @param value valor en texto
     * @return valor booleano válido
     * @throws Exception si el valor no es 'true' o 'false'
     */
    public boolean validateRequiresSpecialist(String value) throws Exception {
        return booleanValidator("requiere especialista", value);
    }

    /**
     * Valida el identificador del tipo de especialista.
     * Regla de negocio: si el procedimiento requiere especialista,
     * este campo debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value              ID del tipo de especialista
     * @param requiresSpecialist indica si se requiere especialista
     * @return ID válido o vacío si no se requiere especialista
     * @throws Exception si se requiere especialista y el valor es inválido
     */
    public String validateSpecialistTypeId(String value, boolean requiresSpecialist) throws Exception {
        if (requiresSpecialist) {
            return stringValidator("ID del tipo de especialista", value);
        }
        return value != null ? value : "";
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
}
