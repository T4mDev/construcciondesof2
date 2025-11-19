package app.adapter.in.validators.patientManagementValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

/**
 * Validador de datos para la entidad EmergencyContact.
 * Se encarga de verificar que los valores relacionados con el contacto de
 * emergencia
 * cumplan con las reglas de negocio antes de ser aceptados en el sistema.
 */
@Component
public class EmergencyContactValidator extends SimpleValidator {

    /**
     * Valida el nombre del contacto de emergencia.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value nombre del contacto
     * @return nombre válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateFirstName(String value) throws Exception {
        return stringValidator("nombre del contacto", value);
    }

    /**
     * Valida el apellido del contacto de emergencia.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value apellido del contacto
     * @return apellido válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateLastName(String value) throws Exception {
        return stringValidator("apellido del contacto", value);
    }

    /**
     * Valida la relación del contacto con el paciente.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value relación con el paciente
     * @return relación válida
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateRelationship(String value) throws Exception {
        return stringValidator("relación con el paciente", value);
    }

    /**
     * Valida el número de teléfono del contacto de emergencia.
     * Regla de negocio: debe tener exactamente 10 dígitos numéricos.
     * 
     * @param value número de teléfono
     * @return número válido
     * @throws Exception si no cumple con la regla
     */
    public String validatePhone(String value) throws Exception {
        if (value == null || !value.matches("\\d{10}")) {
            throw new Exception("El número de teléfono debe tener exactamente 10 dígitos numéricos");
        }
        return value;
    }

    /**
     * Valida el documento del paciente asociado al contacto de emergencia.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value documento del paciente
     * @return documento válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validatePatientDocument(String value) throws Exception {
        return stringValidator("documento del paciente", value);
    }
}
