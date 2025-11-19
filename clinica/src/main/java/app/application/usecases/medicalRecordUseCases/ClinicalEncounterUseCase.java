package app.application.usecases.medicalRecordUseCases;

import app.domain.model.medicalOrders.MedicalOrder;
import app.domain.model.medicalRecords.ClinicalEncounter;
import app.domain.model.patienManagement.HealthMetrics;
import app.domain.services.medicalRecordsServices.ClinicalEncounterService;

import org.springframework.stereotype.Component;

@Component
public class ClinicalEncounterUseCase {

    private final ClinicalEncounterService clinicalEncounterService;

    public ClinicalEncounterUseCase(ClinicalEncounterService clinicalEncounterService) {
        this.clinicalEncounterService = clinicalEncounterService;
    }

    public void createClinicalRecord(ClinicalEncounter record, String patientDocument) throws Exception {
        clinicalEncounterService.create(record, patientDocument);
    }

    public ClinicalEncounter findClinicalRecord(String patientDocument) throws Exception {
        return clinicalEncounterService.findByPatient(patientDocument);
    }

    public void updateHealthMetrics(String patientDocument, HealthMetrics signs) throws Exception {
        clinicalEncounterService.updateHealthMetrics(patientDocument, signs);
    }

    public void attachClinicalOrder(String patientDocument, MedicalOrder order) throws Exception {
        clinicalEncounterService.attachOrder(patientDocument, order);
    }
}
