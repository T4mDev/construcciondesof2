package app.adapter.in.validators.patientManagementValidators;

import app.adapter.in.validators.SimpleValidator;
import app.domain.model.enums.Gender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

/**
 * Validador de datos para la entidad Patient.
 * Se encarga de verificar que los valores básicos del paciente
 * cumplan con las reglas de negocio antes de ser aceptados en el sistema.
 */
@Component
public class PatientValidator extends SimpleValidator {

    /**
     * Valida el nombre completo del paciente.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value nombre completo
     * @return nombre válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateFullName(String value) throws Exception {
        return stringValidator("nombre completo", value);
    }

    /**
     * Valida la fecha de nacimiento del paciente.
     * Regla de negocio: la edad no puede superar los 150 años.
     * 
     * @param value fecha en formato yyyy-MM-dd
     * @return fecha válida
     * @throws Exception si el formato es incorrecto o la edad supera el límite
     */
    public LocalDate validateDateOfBirth(String value) throws Exception {
        try {
            LocalDate date = LocalDate.parse(value);
            int age = Period.between(date, LocalDate.now()).getYears();
            if (age > 150) {
                throw new Exception("La fecha de nacimiento no puede superar los 150 años");
            }
            return date;
        } catch (Exception e) {
            throw new Exception("La fecha ingresada no tiene un formato válido (yyyy-MM-dd)");
        }
    }

    /**
     * Valida el número de teléfono del paciente.
     * Regla de negocio: debe tener exactamente 10 dígitos.
     * 
     * @param value número de teléfono
     * @return número válido
     * @throws Exception si no cumple con la regla
     */
    public String validatePhone(String value) throws Exception {
        if (value == null || !value.matches("\\d{10}")) {
            throw new Exception("El número de teléfono debe tener exactamente 10 dígitos");
        }
        return value;
    }

    /**
     * Valida la dirección del paciente.
     * Regla de negocio: debe ser un texto válido y tener máximo 30 caracteres.
     * 
     * @param value dirección
     * @return dirección válida
     * @throws Exception si no cumple con la regla
     */
    public String validateAddress(String value) throws Exception {
        if (value == null || value.length() > 30) {
            throw new Exception("La dirección debe tener máximo 30 caracteres");
        }
        return value;
    }

    /**
     * Valida el género del paciente.
     * Regla de negocio: debe ser uno de los valores permitidos (MALE, FEMALE,
     * OTHER).
     * 
     * @param value género en texto
     * @return género válido
     * @throws Exception si no corresponde a un valor permitido
     */
    public Gender validateGender(String value) throws Exception {
        try {
            return Gender.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("El género debe ser uno de los siguientes: MALE, FEMALE, OTHER");
        }
    }

    /**
     * Valida el identificador del paciente.
     * Regla de negocio: debe ser un número válido (no nulo ni vacío).
     * 
     * @param value ID en texto
     * @return ID válido
     * @throws Exception si no cumple con la regla
     */
    public long validateId(String value) throws Exception {
        return longValidator("ID del paciente", value);
    }
}
