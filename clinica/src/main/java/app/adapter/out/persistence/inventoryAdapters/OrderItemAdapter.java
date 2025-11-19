package app.adapter.out.persistence.inventoryAdapters;

import app.domain.model.inventory.OrderItem;
import app.domain.ports.inventoryPorts.OrderItemPort;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemAdapter implements OrderItemPort {

    @Override
    public void save(OrderItem item) {
        // implementación mínima
    }

    @Override
    public void delete(OrderItem item) {
        // implementación mínima
    }

    @Override
    public OrderItem findByItemCode(String itemCode) throws Exception {
        return null; // implementación mínima segura
    }
}
