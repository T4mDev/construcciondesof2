package app.application.usecases.patientManagementUseCases;

import app.domain.model.patienManagement.MedicalInsurance;
import app.domain.services.patientManagementServices.MedicalInsuranceService;

import org.springframework.stereotype.Component;

@Component
public class MedicalInsuranceUseCase {

    private final MedicalInsuranceService medicalInsuranceService;

    public MedicalInsuranceUseCase(MedicalInsuranceService medicalInsuranceService) {
        this.medicalInsuranceService = medicalInsuranceService;
    }

    public void createMedicalInsurance(MedicalInsurance insurance) throws Exception {
        medicalInsuranceService.create(insurance);
    }

    public boolean validateMedicalInsurance(String policyNumber) throws Exception {
        return medicalInsuranceService.validatePolicy(policyNumber);
    }

    public boolean isMedicalInsuranceExpired(MedicalInsurance insurance) {
        return medicalInsuranceService.checkExpiration(insurance);
    }

    public MedicalInsurance findMedicalInsuranceByNumber(String policyNumber) throws Exception {
        return medicalInsuranceService.findByPatient(policyNumber);
    }
}
