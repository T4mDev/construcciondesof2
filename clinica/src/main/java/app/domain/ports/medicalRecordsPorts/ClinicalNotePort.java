package app.domain.ports.medicalRecordsPorts;

import app.domain.model.medicalRecords.ClinicalNote;

public interface ClinicalNotePort {
    ClinicalNote findById(ClinicalNote note) throws Exception;

    void save(ClinicalNote note) throws Exception;

    void delete(ClinicalNote note) throws Exception;
}
