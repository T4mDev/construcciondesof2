package app.application.usecases.patientManagementUseCases;

import app.domain.model.patienManagement.Patient;
import app.domain.services.patientManagementServices.PatientService;

import org.springframework.stereotype.Component;

@Component
public class PatientUseCase {

    private final PatientService patientService;

    public PatientUseCase(PatientService patientService) {
        this.patientService = patientService;
    }

    public void createPatient(Patient patient) throws Exception {
        patientService.create(patient);
    }

    public Patient findPatientByDocument(String document) throws Exception {
        return patientService.findByDocument(document);
    }

    public void updatePatientContactInfo(Patient patient) throws Exception {
        patientService.updateContactInfo(patient);
    }

    public void deletePatient(String patient) throws Exception {
        patientService.delete(patient);
    }
}
