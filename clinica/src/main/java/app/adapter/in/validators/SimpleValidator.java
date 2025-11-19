package app.adapter.in.validators;

import app.application.exceptions.InputsException;

/**
 * Validador genérico para distintos tipos de datos.
 * Proporciona métodos comunes para validar cadenas, números y valores
 * booleanos,
 * asegurando que no sean nulos, vacíos o inválidos según las reglas de negocio.
 */
public class SimpleValidator {

    /**
     * Valida que un texto no sea nulo ni vacío.
     * Regla de negocio: el campo debe tener un valor válido.
     * 
     * @param element nombre del campo
     * @param value   valor en texto
     * @return valor válido
     * @throws InputsException si el valor es nulo o vacío
     */
    public String stringValidator(String element, String value) throws Exception {
        if (value == null || value.equals("")) {
            throw new InputsException(element + " no puede tener un valor vacio o nulo");
        }
        return value;
    }

    /**
     * Valida que un texto represente un número entero.
     * Regla de negocio: debe ser un valor numérico válido.
     * 
     * @param element nombre del campo
     * @param value   valor en texto
     * @return número entero válido
     * @throws InputsException si el valor no es numérico
     */
    public int integerValidator(String element, String value) throws Exception {
        stringValidator(element, value);
        try {
            int intValue = Integer.parseInt(value);
            return intValue;
        } catch (Exception e) {
            throw new InputsException(element + " debe ser un valor numerico");
        }
    }

    /**
     * Valida que un texto represente un número largo.
     * Regla de negocio: debe ser un valor numérico válido.
     * 
     * @param element nombre del campo
     * @param value   valor en texto
     * @return número largo válido
     * @throws InputsException si el valor no es numérico
     */
    public long longValidator(String element, String value) throws Exception {
        stringValidator(element, value);
        try {
            long longValue = Long.parseLong(value);
            return longValue;
        } catch (Exception e) {
            throw new InputsException(element + " debe ser un valor numerico");
        }
    }

    /**
     * Valida que un texto represente un número decimal.
     * Regla de negocio: debe ser un valor numérico válido.
     * 
     * @param element nombre del campo
     * @param value   valor en texto
     * @return número decimal válido
     * @throws InputsException si el valor no es numérico
     */
    public double doubleValidator(String element, String value) throws Exception {
        stringValidator(element, value);
        try {
            double doubleValue = Double.parseDouble(value);
            return doubleValue;
        } catch (Exception e) {
            throw new InputsException(element + " debe ser un valor numerico");
        }
    }

    /**
     * Valida que un texto represente un valor booleano.
     * Regla de negocio: debe ser 'true' o 'false'.
     * 
     * @param element nombre del campo
     * @param value   valor en texto
     * @return valor booleano válido
     * @throws InputsException si el valor no es 'true' o 'false'
     */
    public boolean booleanValidator(String element, String value) throws Exception {
        if (value == null) {
            throw new Exception("El campo '" + element + "' no puede estar vacío");
        }
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(value);
        }
        throw new InputsException("El campo '" + element + "' debe ser 'true' o 'false'");
    }
}
