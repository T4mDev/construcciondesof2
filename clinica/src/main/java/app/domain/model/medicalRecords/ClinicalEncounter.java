package app.domain.model.medicalRecords;

import app.domain.model.medicalOrders.MedicalOrder;
import app.domain.model.patienManagement.HealthMetrics;

public class ClinicalEncounter {
    private Long id;
    private String EncounterDetails;
    private String patientDocument;
    private ClinicalNote clinicalNote;
    private MedicalOrder medicalOrder;
    private HealthMetrics healthMetrics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncounterDetails() {
        return EncounterDetails;
    }

    public void setEncounterDetails(String EncounterDetails) {
        this.EncounterDetails = EncounterDetails;
    }

    public String getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(String patientDocument) {
        this.patientDocument = patientDocument;
    }

    public ClinicalNote getClinicalNote() {
        return clinicalNote;
    }

    public void setClinicalNote(ClinicalNote clinicalNote) {
        this.clinicalNote = clinicalNote;
    }

    public MedicalOrder getMedicalOrder() {
        return medicalOrder;
    }

    public void setMedicalOrder(MedicalOrder medicalOrder) {
        this.medicalOrder = medicalOrder;
    }

    public HealthMetrics getHealthMetrics() {
        return healthMetrics;
    }

    public void setHealthMetrics(HealthMetrics healthMetrics) {
        this.healthMetrics = healthMetrics;
    }
}
