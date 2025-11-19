package app.domain.ports.inventoryPorts;

import app.domain.model.inventory.StockItem;
import java.util.Optional;
import java.util.List;

public interface StockItemPort {

    Optional<StockItem> findById(Long id);

    Optional<StockItem> findByItemName(String name);

    List<StockItem> findByType(String type);

    List<StockItem> findAll();

    StockItem save(StockItem item);

    void delete(Long id);

    boolean isEmpty();
}
