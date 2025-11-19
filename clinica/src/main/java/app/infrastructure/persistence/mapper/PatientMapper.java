package app.infrastructure.persistence.mapper;

import app.domain.model.patienManagement.Patient;
import app.infrastructure.persistence.entities.PatientEntity;

public class PatientMapper {

    public static PatientEntity toEntity(Patient patient) {
        PatientEntity entity = new PatientEntity();
        entity.setId(patient.getId());
        entity.setFullName(patient.getFullName());
        entity.setGender(patient.getGender());
        entity.setBirthDate(patient.getBirthDate());
        entity.setPhone(patient.getPhone());
        entity.setAddress(patient.getAddress());
        entity.setDocument(patient.getDocument());
        return entity;
    }

    public static Patient toDomain(PatientEntity entity) {
        Patient patient = new Patient();
        patient.setId(entity.getId());
        patient.setFullName(entity.getFullName());
        patient.setGender(entity.getGender());
        patient.setBirthDate(entity.getBirthDate());
        patient.setPhone(entity.getPhone());
        patient.setAddress(entity.getAddress());
        patient.setDocument(entity.getDocument());
        return patient;
    }
}
