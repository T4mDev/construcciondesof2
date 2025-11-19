package app.infrastructure.persistence.repository;

import app.domain.model.medicalOrders.MedicalOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalOrderRepository extends JpaRepository<MedicalOrder, Long> {
}
