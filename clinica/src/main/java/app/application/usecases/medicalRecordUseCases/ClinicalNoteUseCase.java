package app.application.usecases.medicalRecordUseCases;

import app.domain.model.medicalRecords.ClinicalNote;
import app.domain.services.medicalRecordsServices.ClinicalNoteService;

import org.springframework.stereotype.Component;

@Component
public class ClinicalNoteUseCase {

    private final ClinicalNoteService clinicalNoteService;

    public ClinicalNoteUseCase(ClinicalNoteService clinicalNoteService) {
        this.clinicalNoteService = clinicalNoteService;
    }

    public void createClinicalNote(ClinicalNote note) throws Exception {
        clinicalNoteService.create(note);
    }

    public ClinicalNote findClinicalNoteByPatient(long patientId) throws Exception {
        return clinicalNoteService.findByPatient(patientId);
    }

    public void deleteClinicalNote(ClinicalNote note) throws Exception {
        clinicalNoteService.delete(note);
    }

    public void updateClinicalNote(ClinicalNote note) throws Exception {
        clinicalNoteService.updateNote(note);
    }
}
