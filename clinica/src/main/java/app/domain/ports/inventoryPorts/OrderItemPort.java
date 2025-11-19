package app.domain.ports.inventoryPorts;

import app.domain.model.inventory.OrderItem;

public interface OrderItemPort {
    OrderItem findByItemCode(String itemCode) throws Exception;

    void save(OrderItem item) throws Exception;

    void delete(OrderItem item) throws Exception;
}
