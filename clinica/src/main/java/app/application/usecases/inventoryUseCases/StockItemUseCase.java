package app.application.usecases.inventoryUseCases;

import app.domain.model.inventory.StockItem;
import app.domain.services.inventoryServices.StockItemService;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StockItemUseCase {
    public List<StockItem> findAllItems() throws Exception {
        // Si el servicio no tiene un método directo, puedes obtener todos por tipo null
        // o similar
        return stockItemService.findByType(null);
    }

    private final StockItemService stockItemService;

    public StockItemUseCase(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    public void createItem(StockItem item) throws Exception {
        stockItemService.create(item);
    }

    public void updateItem(StockItem item) throws Exception {
        stockItemService.update(item);
    }

    public void deleteItem(Long item) throws Exception {
        stockItemService.delete(item);
    }

    public List<StockItem> findItemsByCategory(String type) throws Exception {
        return stockItemService.findByType(type);
    }
}
