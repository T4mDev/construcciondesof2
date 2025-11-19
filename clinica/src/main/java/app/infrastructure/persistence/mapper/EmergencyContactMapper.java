package app.infrastructure.persistence.mapper;

import app.domain.model.patienManagement.EmergencyContact;
import app.infrastructure.persistence.entities.EmergencyContactEntity;

public class EmergencyContactMapper {

    public static EmergencyContact toDomain(EmergencyContactEntity entity) {
        if (entity == null)
            return null;

        EmergencyContact contact = new EmergencyContact();
        contact.setId(entity.getId());
        contact.setPatientDocument(entity.getPatientDocument());
        contact.setFirstName(entity.getFirstName());
        contact.setLastName(entity.getLastName());
        contact.setRelationship(entity.getRelationship());
        contact.setPhone(entity.getPhone());
        return contact;
    }

    public static EmergencyContactEntity toEntity(EmergencyContact contact) {
        if (contact == null)
            return null;

        EmergencyContactEntity entity = new EmergencyContactEntity();
        entity.setId(contact.getId());
        entity.setPatientDocument(contact.getPatientDocument());
        entity.setFirstName(contact.getFirstName());
        entity.setLastName(contact.getLastName());
        entity.setRelationship(contact.getRelationship());
        entity.setPhone(contact.getPhone());
        return entity;
    }
}
