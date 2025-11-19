package app.adapter.in.validators.peopleValidators;

import app.adapter.in.validators.SimpleValidator;
import app.domain.model.enums.Role;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

/**
 * Validador de datos para la entidad Usuario.
 * Se encarga de verificar que los valores ingresados cumplan con las reglas
 * básicas
 * de negocio y formato antes de ser aceptados en el sistema.
 */
@Component
public class UserValidator extends SimpleValidator {

    /**
     * Valida el ID del usuario.
     * 
     * @param value valor en texto
     * @return ID convertido a número
     * @throws Exception si el valor no es un número válido
     */
    public long validateId(String value) throws Exception {
        return longValidator("ID del usuario", value);
    }

    /**
     * Valida el nombre de usuario.
     * Regla de negocio: máximo 15 caracteres, solo letras y números.
     * 
     * @param value nombre de usuario
     * @return nombre válido
     * @throws Exception si no cumple con la regla
     */
    public String validateUsername(String value) throws Exception {
        if (value == null || !value.matches("^[a-zA-Z0-9]{1,15}$")) {
            throw new Exception("El nombre de usuario debe tener máximo 15 caracteres, solo letras y números");
        }
        return value;
    }

    /**
     * Valida la contraseña del usuario.
     * Regla de negocio: mínimo 8 caracteres, al menos una mayúscula,
     * un número y un carácter especial.
     * 
     * @param value contraseña
     * @return contraseña válida
     * @throws Exception si no cumple con la regla
     */
    public String validatePassword(String value) throws Exception {
        if (value == null ||
                value.length() < 8 ||
                !value.matches(".*[A-Z].*") ||
                !value.matches(".*\\d.*") ||
                !value.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            throw new Exception(
                    "La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial");
        }
        return value;
    }

    /**
     * Valida el correo electrónico.
     * Regla de negocio: debe cumplir con formato estándar de email.
     * 
     * @param value correo electrónico
     * @return correo válido
     * @throws Exception si no cumple con la regla
     */
    public String validateEmail(String value) throws Exception {
        if (value == null || !value.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new Exception("El correo electrónico no es válido");
        }
        return value;
    }

    /**
     * Valida la fecha de nacimiento.
     * Regla de negocio: la edad no puede superar los 150 años.
     * 
     * @param value fecha en formato yyyy-MM-dd
     * @return fecha válida
     * @throws Exception si el formato es incorrecto o la edad supera el límite
     */
    public LocalDate validateBirthDate(String value) throws Exception {
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
     * Valida el rol del usuario.
     * Regla de negocio: debe ser uno de los roles definidos en el sistema
     * ( HUMAN_RESOURCES,
     * ADMINISTRATIVE_STAFF,
     * INFORMATION_SUPPORT,
     * NURSES,
     * DOCTORS).
     * 
     * @param value rol en texto
     * @return rol válido
     * @throws Exception si no corresponde a un rol permitido
     */
    public Role validateRole(String value) throws Exception {
        try {
            return Role.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception(
                    "El rol debe ser uno de los siguientes:     HUMAN_RESOURCES,ADMINISTRATIVE_STAFF,INFORMATION_SUPPORT,NURSES,DOCTORS");
        }
    }
}
