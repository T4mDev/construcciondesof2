package app.domain.services.inventoryServices;

import app.domain.model.inventory.OrderItem;
import app.domain.ports.inventoryPorts.OrderItemPort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio para la gestión de ítems de orden.
 * Encapsula las reglas de negocio relacionadas con la creación, validación,
 * búsqueda y eliminación de ítems, delegando la persistencia en
 * {@link OrderItemPort}.
 */
@Service
public class OrderItemService {

    @Autowired
    private final OrderItemPort orderItemPort;

    /**
     * Constructor que inyecta el puerto de persistencia de ítems de orden.
     * 
     * @param orderItemPort instancia de {@link OrderItemPort}
     */
    public OrderItemService(OrderItemPort orderItemPort) {
        this.orderItemPort = orderItemPort;
    }

    /**
     * Crea un nuevo ítem de orden.
     * Reglas de negocio:
     * <ul>
     * <li>El código de ítem es obligatorio.</li>
     * <li>La categoría de ítem es obligatoria.</li>
     * <li>El ID vinculado es obligatorio.</li>
     * <li>No puede existir otro ítem con el mismo código.</li>
     * </ul>
     * 
     * @param item objeto {@link OrderItem} a crear
     * @throws Exception si alguna regla de negocio es violada
     */
    public void create(OrderItem item) throws Exception {
        validateItemUniqueness(item);

        if (item.getItemCode() == null || item.getItemCode().isEmpty()) {
            throw new Exception("El código de ítem es obligatorio");
        }

        if (item.getCategory() == null || item.getCategory().isEmpty()) {
            throw new Exception("La categoría de ítem es obligatoria");
        }

        if (item.getLinkedId() == null || item.getLinkedId().isEmpty()) {
            throw new Exception("El ID vinculado es obligatorio");
        }

        orderItemPort.save(item);
    }

    /**
     * Valida que el ítem sea único en el sistema.
     * Regla de negocio: no puede existir otro ítem con el mismo código.
     * 
     * @param item objeto {@link OrderItem} a validar
     * @throws Exception si ya existe un ítem con el mismo código
     */
    public void validateItemUniqueness(OrderItem item) throws Exception {
        OrderItem existing = orderItemPort.findByItemCode(item.getItemCode());
        if (existing != null) {
            throw new Exception("Ya existe un ítem con ese código");
        }
    }

    /**
     * Busca ítems por categoría.
     * Nota: este método debe implementarse en el adaptador correspondiente.
     * 
     * @param category categoría de ítem
     * @return lista de ítems de la categoría
     * @throws UnsupportedOperationException siempre, hasta que se implemente en el
     *                                       adaptador
     */
    public List<OrderItem> findByCategory(String category) throws Exception {
        throw new UnsupportedOperationException("findByCategory debe implementarse en el adaptador");
    }

    /**
     * Elimina un ítem existente.
     * Regla de negocio: el ítem debe existir en el sistema.
     * 
     * @param item objeto {@link OrderItem} a eliminar
     * @throws Exception si el ítem no existe
     */
    public void delete(OrderItem item) throws Exception {
        OrderItem existing = orderItemPort.findByItemCode(item.getItemCode());
        if (existing == null) {
            throw new Exception("No se encontró el ítem para eliminar");
        }

        orderItemPort.delete(item);
    }
}
