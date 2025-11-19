package app.domain.ports.medicalRecordsPorts;

import app.domain.model.medicalRecords.ClinicalEncounter;

public interface ClinicalEncounterPort {
    ClinicalEncounter findByPatientId(String patientDocument) throws Exception;

    void save(ClinicalEncounter record) throws Exception;

    void delete(ClinicalEncounter record) throws Exception;
}