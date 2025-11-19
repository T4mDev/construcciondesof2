package app.domain.services.medicalOrdersServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.inventory.OrderItem;
import app.domain.model.medicalOrders.MedicalOrder;
import app.domain.ports.medicalOrdersPorts.MedicalOrderPort;
import java.util.Optional;

/**
 * Servicio de dominio para la gestión de órdenes médicas.
 * Encapsula las reglas de negocio relacionadas con la creación, modificación,
 * validación y finalización de órdenes médicas, delegando la persistencia
 * en {@link MedicalOrderPort}.
 */
@Service
public class MedicalOrderService {

    @Autowired
    private final MedicalOrderPort medicalOrderPort;

    /**
     * Constructor que inyecta el puerto de persistencia de órdenes médicas.
     * 
     * @param medicalOrderPort instancia de {@link MedicalOrderPort}
     */
    public MedicalOrderService(MedicalOrderPort medicalOrderPort) {
        this.medicalOrderPort = medicalOrderPort;
    }

    /**
     * Crea una nueva orden médica.
     * Reglas de negocio:
     * <ul>
     * <li>No puede existir otra orden con el mismo número.</li>
     * <li>La orden debe contener al menos un ítem.</li>
     * </ul>
     * 
     * @param order objeto {@link MedicalOrder} a crear
     * @throws Exception si ya existe una orden con el mismo número o no contiene
     *                   ítems
     */
    public void create(MedicalOrder order) throws Exception {
        // Si el order tiene un ID, comprobamos si ya existe
        if (order.getId() != null) {
            Optional<MedicalOrder> existing = medicalOrderPort.findById(order.getId());
            if (existing.isPresent()) {
                throw new Exception("Ya existe una orden con ese ID");
            }
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new Exception("La orden debe contener al menos un ítem");
        }

        medicalOrderPort.save(order);
    }

    /**
     * Agrega un ítem a una orden médica existente.
     * Reglas de negocio:
     * <ul>
     * <li>La orden debe existir.</li>
     * <li>No puede agregarse un ítem duplicado (mismo código).</li>
     * </ul>
     * 
     * @param orderNumber número de la orden
     * @param item        objeto {@link OrderItem} a agregar
     * @throws Exception si la orden no existe o el ítem ya está presente
     */
    public void addItem(String orderNumber, OrderItem item) throws Exception {
        Long id;
        try {
            id = Long.parseLong(orderNumber);
        } catch (NumberFormatException e) {
            throw new Exception("Número de orden inválido: debe ser numérico");
        }

        Optional<MedicalOrder> orderOpt = medicalOrderPort.findById(id);
        if (orderOpt.isEmpty()) {
            throw new Exception("No se encontró la orden médica");
        }

        MedicalOrder order = orderOpt.get();

        boolean duplicate = order.getItems().stream()
                .anyMatch(existing -> existing.getItemCode().equals(item.getItemCode()));

        if (duplicate) {
            throw new Exception("Ya existe un ítem con ese número en la orden");
        }

        order.getItems().add(item);
        medicalOrderPort.save(order);
    }

    /**
     * Elimina un ítem de una orden médica existente.
     * Reglas de negocio:
     * <ul>
     * <li>La orden debe existir.</li>
     * <li>El ítem debe estar presente en la orden.</li>
     * </ul>
     * 
     * @param orderNumber número de la orden
     * @param itemNumber  código del ítem a eliminar
     * @throws Exception si la orden no existe o el ítem no se encuentra
     */
    public void removeItem(String orderNumber, String itemNumber) throws Exception {
        Long id;
        try {
            id = Long.parseLong(orderNumber);
        } catch (NumberFormatException e) {
            throw new Exception("Número de orden inválido: debe ser numérico");
        }

        Optional<MedicalOrder> orderOpt = medicalOrderPort.findById(id);
        if (orderOpt.isEmpty()) {
            throw new Exception("No se encontró la orden médica");
        }

        MedicalOrder order = orderOpt.get();

        boolean removed = order.getItems().removeIf(item -> item.getItemCode().equals(itemNumber));
        if (!removed) {
            throw new Exception("No se encontró el ítem en la orden");
        }

        medicalOrderPort.save(order);
    }

    /**
     * Valida que una orden médica cumpla con las reglas de negocio.
     * Reglas de negocio:
     * <ul>
     * <li>La orden debe contener al menos un ítem.</li>
     * <li>Cada ítem debe tener un código válido (no nulo ni vacío).</li>
     * </ul>
     * 
     * @param order objeto {@link MedicalOrder} a validar
     * @throws Exception si la orden no cumple con las reglas
     */
    public void validateOrder(MedicalOrder order) throws Exception {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new Exception("La orden debe tener al menos un ítem");
        }

        for (OrderItem item : order.getItems()) {
            if (item.getItemCode() == null || item.getItemCode().isEmpty()) {
                throw new Exception("Cada ítem debe tener un número válido");
            }
        }
    }

    /**
     * Finaliza una orden médica validándola antes de persistirla.
     * Regla de negocio: la orden debe ser válida según
     * {@link #validateOrder(MedicalOrder)}.
     * 
     * @param order objeto {@link MedicalOrder} a finalizar
     * @throws Exception si la orden no cumple con las reglas de negocio
     */
    public void finalizeOrder(MedicalOrder order) throws Exception {
        validateOrder(order);
        medicalOrderPort.save(order);
    }
}
