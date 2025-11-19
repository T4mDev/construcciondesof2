package app.adapter.in.validators.inventoryValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

/**
 * Validador de datos para la entidad StockItem.
 * Se encarga de verificar que los valores básicos del ítem de inventario
 * no sean nulos y cumplan con formato de texto válido.
 */
@Component
public class StockItemValidator extends SimpleValidator {

    /**
     * Valida el identificador del ítem de inventario.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value ID en texto
     * @return ID válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateId(String value) throws Exception {
        return stringValidator("ID del ítem de inventario", value);
    }

    /**
     * Valida el nombre del ítem.
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
     * Valida el tipo del ítem.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value tipo del ítem
     * @return tipo válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateType(String value) throws Exception {
        return stringValidator("tipo del ítem", value);
    }
}
