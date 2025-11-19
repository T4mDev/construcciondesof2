package app.infrastructure.persistence.mapper;

import app.domain.model.medicalRecords.ClinicalEncounter;
import app.infrastructure.persistence.entities.ClinicalEncounterEntity;

public class ClinicalEncounterMapper {

    public static ClinicalEncounter toDomain(ClinicalEncounterEntity entity) {
        if (entity == null)
            return null;

        ClinicalEncounter domain = new ClinicalEncounter();
        domain.setId(entity.getId());
        domain.setEncounterDetails(entity.getEncounterDetails());
        domain.setPatientDocument(entity.getPatientDocument());
        return domain;
    }

    public static ClinicalEncounterEntity toEntity(ClinicalEncounter domain) {
        if (domain == null)
            return null;

        ClinicalEncounterEntity entity = new ClinicalEncounterEntity();
        entity.setId(domain.getId());
        entity.setEncounterDetails(domain.getEncounterDetails());
        entity.setPatientDocument(domain.getPatientDocument());
        return entity;
    }
}
