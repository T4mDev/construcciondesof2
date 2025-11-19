package app.adapter.in.validators.medicalOrderValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;
import app.domain.model.inventory.OrderItem;

import java.util.List;

/**
 * Validador de datos para la entidad MedicalOrder.
 * Se encarga de verificar que las órdenes médicas tengan un número válido
 * y que contengan ítems correctos antes de ser aceptadas en el sistema.
 */
@Component
public class MedicalOrderValidator extends SimpleValidator {

    /**
     * Valida el número de la orden médica.
     * Regla de negocio: debe ser un texto válido (no nulo ni vacío).
     * 
     * @param value número de orden
     * @return número válido
     * @throws Exception si el valor no cumple con la regla
     */
    public String validateOrderNumber(String value) throws Exception {
        return stringValidator("número de orden", value);
    }

    /**
     * Valida los ítems de la orden médica.
     * Reglas de negocio:
     * - La orden debe contener al menos un ítem.
     * - Cada ítem debe tener un número válido (no nulo ni vacío).
     * 
     * @param items lista de ítems de la orden
     * @throws Exception si la lista está vacía/nula o si algún ítem no tiene número
     *                   válido
     */
    public void validateItems(List<OrderItem> items) throws Exception {
        if (items == null || items.isEmpty()) {
            throw new Exception("La orden debe contener al menos un ítem");
        }

        for (OrderItem item : items) {
            if (item.getItemCode() == null || item.getItemCode().isEmpty()) {
                throw new Exception("Cada ítem debe tener un número válido");
            }
        }
    }
}
