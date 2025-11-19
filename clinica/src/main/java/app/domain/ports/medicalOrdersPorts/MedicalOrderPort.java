package app.domain.ports.medicalOrdersPorts;

import app.domain.model.medicalOrders.MedicalOrder;
import java.util.Optional;
import java.util.List;

public interface MedicalOrderPort {

    Optional<MedicalOrder> findById(Long id);

    List<MedicalOrder> findAll();

    MedicalOrder save(MedicalOrder order);

    void delete(Long id);
}
