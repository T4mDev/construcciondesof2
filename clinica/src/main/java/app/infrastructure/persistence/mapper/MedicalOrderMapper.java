package app.infrastructure.persistence.mapper;

import app.domain.model.medicalOrders.MedicalOrder;
import app.infrastructure.persistence.entities.MedicalOrderEntity;

public class MedicalOrderMapper {
    public static MedicalOrderEntity toEntity(MedicalOrder medicalOrder) {
        if (medicalOrder == null)
            return null;
        MedicalOrderEntity entity = new MedicalOrderEntity();
        entity.setId(medicalOrder.getId());
        entity.setOrderDetails(medicalOrder.getOrderDetails());
        return entity;
    }

    public static MedicalOrder toDomain(MedicalOrderEntity entity) {
        if (entity == null)
            return null;
        MedicalOrder medicalOrder = new MedicalOrder();
        medicalOrder.setId(entity.getId());
        medicalOrder.setOrderDetails(entity.getOrderDetails());
        return medicalOrder;
    }
}
