package app.infrastructure.persistence.mapper;

import app.domain.model.medicalRecords.MedicalRecord;
import app.infrastructure.persistence.entities.MedicalRecordEntity;

public class MedicalRecordMapper {

    public static MedicalRecord toDomain(MedicalRecordEntity e) {
        if (e == null)
            return null;

        MedicalRecord r = new MedicalRecord();
        r.setId(e.getId());
        r.setPatientDocument(e.getPatientDocument());
            r.setGeneralDetails(e.getRecordDetails());

        // Cuando tengas entries se agregan acá
        return r;
    }

    public static MedicalRecordEntity toEntity(MedicalRecord d) {
        if (d == null)
            return null;

        MedicalRecordEntity e = new MedicalRecordEntity();
        e.setId(d.getId());
        e.setPatientDocument(d.getPatientDocument());
            e.setPatientName(""); // O asigna un valor si lo tienes
            e.setRecordDetails(d.getGeneralDetails());

        return e;
    }
}
