package app.domain.ports.medicalOrdersPorts;

import app.domain.model.medicalOrders.Prescription;

public interface PrescriptionPort {
    Prescription findById(String id) throws Exception;

    void save(Prescription prescription) throws Exception;

    void delete(Prescription prescription) throws Exception;
}
