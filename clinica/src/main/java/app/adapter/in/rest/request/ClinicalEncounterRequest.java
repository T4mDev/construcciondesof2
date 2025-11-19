package app.adapter.in.rest.request;

import app.domain.model.medicalOrders.MedicalOrder;
import app.domain.model.medicalRecords.ClinicalNote;
import app.domain.model.patienManagement.HealthMetrics;

public class ClinicalEncounterRequest {

    private Long id;
    private String EncounterDetails;
    private String patientDocument;
    private ClinicalNote clinicalNote;
    private MedicalOrder medicalOrder;
    private HealthMetrics healthMetrics;

    public ClinicalEncounterRequest() {
    }

    public ClinicalEncounterRequest(Long id, String EncounterDetails, String patientDocument,
            ClinicalNote clinicalNote, MedicalOrder medicalOrder, HealthMetrics healthMetrics) {
        this.id = id;
        this.EncounterDetails = EncounterDetails;
        this.patientDocument = patientDocument;
        this.clinicalNote = clinicalNote;
        this.medicalOrder = medicalOrder;
        this.healthMetrics = healthMetrics;
    }

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
