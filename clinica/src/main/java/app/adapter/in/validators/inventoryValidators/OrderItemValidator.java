package app.adapter.in.validators.inventoryValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

/**
 * Validador de datos para la entidad OrderItem.
 * Se encarga de verificar que los valores básicos de un ítem dentro de una
 * orden
 * sean válidos antes de ser aceptados en el sistema.
 */
@Component
public class OrderItemValidator extends SimpleValidator {

    /**
     * Valida el número de ítem.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value número de ítem
     * @return número válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateItemCode(String value) throws Exception {
        return stringValidator("Número de ítem", value);
    }

    /**
     * Valida el tipo de ítem.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value tipo de ítem
     * @return tipo válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateCategory(String value) throws Exception {
        return stringValidator("Tipo de ítem", value);
    }

    /**
     * Valida el identificador de referencia del ítem.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value ID de referencia
     * @return ID válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateLinkedId(String value) throws Exception {
        return stringValidator("ID de referencia", value);
    }
}
