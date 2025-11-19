package app.domain.ports.patienManagementPorts;

import app.domain.model.patienManagement.Patient;

import java.util.Optional;
import java.util.List;

public interface PatientPort {

    Optional<Patient> findById(long id);

    Optional<Patient> findByDocument(String document);

    List<Patient> findAll();

    Patient save(Patient patient);

    void delete(long id);

    boolean isEmpty();
}
