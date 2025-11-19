package app.application.usecases.inventoryUseCases;

import app.domain.model.inventory.OrderItem;
import app.domain.services.inventoryServices.OrderItemService;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OrderItemUseCase {

    private final OrderItemService orderItemService;

    public OrderItemUseCase(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    public void createOrderItem(OrderItem item) throws Exception {
        orderItemService.create(item);
    }

    public void validateOrderItemUniqueness(OrderItem item) throws Exception {
        orderItemService.validateItemUniqueness(item);
    }

    public List<OrderItem> findOrderItemsByType(String type) throws Exception {
        return orderItemService.findByCategory(type);
    }

    public void deleteOrderItem(OrderItem item) throws Exception {
        orderItemService.delete(item);
    }
}
