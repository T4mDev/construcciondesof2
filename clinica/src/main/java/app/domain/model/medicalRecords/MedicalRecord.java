package app.domain.model.medicalRecords;

import java.time.LocalDate;

public class MedicalRecord {
    private Long id;
    private String patientDocument;
    private LocalDate creationDate;
    private String generalDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(String patientDocument) {
        this.patientDocument = patientDocument;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getGeneralDetails() {
        return generalDetails;
    }

    public void setGeneralDetails(String generalDetails) {
        this.generalDetails = generalDetails;
    }
}
