package app.domain.ports.patienManagementPorts;

import app.domain.model.patienManagement.EmergencyContact;

import java.util.List;
import java.util.Optional;

public interface EmergencyContactPort {

    Optional<EmergencyContact> findById(Long id);

    Optional<EmergencyContact> findByPatientDocument(String patientDocument);

    List<EmergencyContact> findAll();

    EmergencyContact save(EmergencyContact contact);

    void delete(EmergencyContact contact);
}
