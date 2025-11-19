package app.adapter.in.validators.peopleValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

/**
 * Validador de datos para la entidad Specialty.
 * Se encarga de verificar que los valores básicos del tipo de especialista
 * sean válidos antes de ser aceptados en el sistema.
 */
@Component
public class SpecialtyValidator extends SimpleValidator {

    /**
     * Valida el identificador del tipo de especialista.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value ID en texto
     * @return ID válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateId(String value) throws Exception {
        return stringValidator("ID del tipo de especialista", value);
    }

    /**
     * Valida el nombre del tipo de especialista.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value nombre del especialista
     * @return nombre válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateName(String value) throws Exception {
        return stringValidator("nombre del tipo de especialista", value);
    }
}