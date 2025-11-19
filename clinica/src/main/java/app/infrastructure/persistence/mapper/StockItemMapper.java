package app.infrastructure.persistence.mapper;

import app.domain.model.inventory.StockItem;
import app.infrastructure.persistence.entities.StockItemEntity;

public class StockItemMapper {

    public static StockItemEntity toEntity(StockItem domain) {
        if (domain == null)
            return null;

        StockItemEntity entity = new StockItemEntity();
        entity.setId(domain.getId());
        entity.setItemName(domain.getItemName());
        entity.setQuantity(domain.getQuantity());
        entity.setItemType(domain.getItemType());
        return entity;
    }

    public static StockItem toDomain(StockItemEntity entity) {
        if (entity == null)
            return null;

        StockItem domain = new StockItem();
        domain.setId(entity.getId());
        domain.setItemName(entity.getItemName());
        domain.setQuantity(entity.getQuantity());
        domain.setItemType(entity.getItemType());
        return domain;
    }
}
