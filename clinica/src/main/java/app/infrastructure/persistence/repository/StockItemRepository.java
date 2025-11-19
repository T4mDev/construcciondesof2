package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.StockItemEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends JpaRepository<StockItemEntity, Long> {

    Optional<StockItemEntity> findByItemName(String itemName);
}
