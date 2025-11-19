package app.application.usecases.medicalOrderUseCases;

import app.domain.model.inventory.OrderItem;
import app.domain.model.medicalOrders.MedicalOrder;
import app.domain.services.medicalOrdersServices.MedicalOrderService;

import org.springframework.stereotype.Component;

@Component
public class MedicalOrderUseCase {

    private final MedicalOrderService medicalOrderService;

    public MedicalOrderUseCase(MedicalOrderService medicalOrderService) {
        this.medicalOrderService = medicalOrderService;
    }

    public void createMedicalOrder(MedicalOrder order) throws Exception {
        medicalOrderService.create(order);
    }

    public void addItemToOrder(String orderNumber, OrderItem item) throws Exception {
        medicalOrderService.addItem(orderNumber, item);
    }

    public void removeItemFromOrder(String orderNumber, String itemNumber) throws Exception {
        medicalOrderService.removeItem(orderNumber, itemNumber);
    }

    public void validateMedicalOrder(MedicalOrder order) throws Exception {
        medicalOrderService.validateOrder(order);
    }

    public void finalizeMedicalOrder(MedicalOrder order) throws Exception {
        medicalOrderService.finalizeOrder(order);
    }
}
