package app.adapter.out.persistence.medicalOrderAdapters;

import app.domain.model.medicalOrders.Prescription;
import app.domain.ports.medicalOrdersPorts.PrescriptionPort;
import org.springframework.stereotype.Repository;

@Repository
public class PrescriptionAdapter implements PrescriptionPort {

    @Override
    public void save(Prescription prescription) {
        // implementación mínima
    }

    @Override
    public void delete(Prescription prescription) {
        // implementación mínima
    }

    @Override
    public Prescription findById(String id) throws Exception {
        return null; // implementación mínima segura
    }
}
