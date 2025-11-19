
package app.infrastructure.persistence.mapper;

import app.domain.model.patienManagement.MedicalInsurance;
import app.infrastructure.persistence.entities.MedicalInsuranceEntity;

public class MedicalInsuranceMapper {

    public static MedicalInsuranceEntity toEntity(MedicalInsurance medicalInsurance) {
        if (medicalInsurance == null)
            return null;
        MedicalInsuranceEntity entity = new MedicalInsuranceEntity();
        entity.setId(medicalInsurance.getId());
        entity.setCompanyName(medicalInsurance.getCompanyName());
        entity.setPolicyNumber(medicalInsurance.getPolicyNumber());
        entity.setActive(medicalInsurance.isActive());
        entity.setEndDate(medicalInsurance.getEndDate());
        entity.setInsurer(medicalInsurance.getInsurer());
        return entity;
    }

    public static MedicalInsurance toDomain(MedicalInsuranceEntity entity) {
        if (entity == null)
            return null;
        MedicalInsurance medicalInsurance = new MedicalInsurance();
        medicalInsurance.setId(entity.getId());
        medicalInsurance.setCompanyName(entity.getCompanyName());
        medicalInsurance.setPolicyNumber(entity.getPolicyNumber());
        medicalInsurance.setActive(entity.isActive());
        medicalInsurance.setEndDate(entity.getEndDate());
        medicalInsurance.setInsurer(entity.getInsurer());
        return medicalInsurance;
    }
}
