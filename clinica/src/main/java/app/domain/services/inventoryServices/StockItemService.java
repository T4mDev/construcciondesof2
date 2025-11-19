package app.domain.services.inventoryServices;

import app.domain.model.inventory.StockItem;
import app.domain.ports.inventoryPorts.StockItemPort;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * Servicio de dominio para la gestión de ítems de inventario.
 * Encapsula las reglas de negocio relacionadas con creación, búsqueda,
 * actualización y eliminación de ítems.
 */
@Service
public class StockItemService {

    private final StockItemPort stockItemPort;

    public StockItemService(StockItemPort stockItemPort) {
        this.stockItemPort = stockItemPort;
    }

    /**
     * Crea un nuevo ítem de inventario.
     * Reglas de negocio:
     * <ul>
     * <li>Nombre obligatorio.</li>
     * <li>Tipo obligatorio.</li>
     * <li>No puede existir otro ítem con el mismo nombre y tipo.</li>
     * </ul>
     */
    public StockItem create(StockItem item) throws Exception {

        if (item.getItemName() == null || item.getItemName().isBlank()) {
            throw new Exception("El nombre del ítem es obligatorio");
        }

        // Evitar duplicados (si así lo quieres)
        Optional<StockItem> existing = stockItemPort.findByItemName(item.getItemName());
        if (existing.isPresent()) {
            throw new Exception("Ya existe un ítem con ese nombre");
        }

        return stockItemPort.save(item);
    }

    /**
     * Busca ítems por tipo.
     */
    public List<StockItem> findByType(String type) {
        return stockItemPort.findByType(type);
    }

    /**
     * Actualiza un ítem existente.
     */
    public StockItem update(StockItem item) throws Exception {

        if (item.getId() == null) {
            throw new Exception("El ID es obligatorio para actualizar");
        }

        Optional<StockItem> existing = stockItemPort.findById(item.getId());
        if (existing.isEmpty()) {
            throw new Exception("No se encontró el ítem para actualizar");
        }

        return stockItemPort.save(item);
    }

    /**
     * Elimina un ítem por ID.
     */
    public void delete(Long id) throws Exception {
        Optional<StockItem> existing = stockItemPort.findById(id);
        if (existing.isEmpty()) {
            throw new Exception("No se encontró el ítem para eliminar");
        }

        stockItemPort.delete(id);
    }
}
