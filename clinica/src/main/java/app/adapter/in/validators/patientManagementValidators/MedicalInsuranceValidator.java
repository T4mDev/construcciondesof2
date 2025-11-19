package app.adapter.in.validators.patientManagementValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

import java.time.LocalDate;

/**
 * Validador de datos para la entidad MedicalInsurance.
 * Se encarga de verificar que los valores relacionados con una póliza médica
 * cumplan con las reglas de negocio antes de ser aceptados en el sistema.
 */
@Component
public class MedicalInsuranceValidator extends SimpleValidator {

    /**
     * Valida el identificador de la póliza.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value ID de la póliza
     * @return ID válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateId(String value) throws Exception {
        return stringValidator("ID de la póliza", value);
    }

    /**
     * Valida el nombre de la compañía aseguradora.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value nombre de la compañía
     * @return nombre válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateCompanyName(String value) throws Exception {
        return stringValidator("nombre de la compañía aseguradora", value);
    }

    /**
     * Valida el número de póliza.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value número de póliza
     * @return número válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validatePolicyNumber(String value) throws Exception {
        return stringValidator("número de póliza", value);
    }

    /**
     * Valida el estado activo de la póliza.
     * Regla de negocio: debe ser 'true' o 'false'.
     * 
     * @param value estado en texto
     * @return estado válido
     * @throws Exception si el valor no es 'true' o 'false'
     */
    public boolean validateActiveStatus(String value) throws Exception {
        return booleanValidator("estado activo de la póliza", value);
    }

    /**
     * Valida la fecha de vigencia de la póliza.
     * Regla de negocio: debe ser futura y tener formato válido (yyyy-MM-dd).
     * 
     * @param value fecha en texto
     * @return fecha válida
     * @throws Exception si la fecha es pasada o el formato es incorrecto
     */
    public LocalDate validateEndDate(String value) throws Exception {
        try {
            LocalDate date = LocalDate.parse(value);
            if (date.isBefore(LocalDate.now())) {
                throw new Exception("La fecha de vigencia debe ser futura");
            }
            return date;
        } catch (Exception e) {
            throw new Exception("La fecha ingresada no tiene un formato válido (yyyy-MM-dd)");
        }
    }
}
