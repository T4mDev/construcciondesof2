package app.domain.ports.medicalRecordsPorts;

import app.domain.model.medicalRecords.MedicalRecord;
import java.util.Optional;
import java.util.List;

public interface MedicalRecordPort {

    MedicalRecord save(MedicalRecord record);

    Optional<MedicalRecord> findById(Long id);

    Optional<MedicalRecord> findByPatientDocument(String document);

    List<MedicalRecord> findAll();

    void delete(Long id);

    boolean isEmpty();
}
