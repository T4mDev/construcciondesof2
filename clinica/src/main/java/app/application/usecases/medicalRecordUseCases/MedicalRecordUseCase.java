package app.application.usecases.medicalRecordUseCases;

import app.domain.model.medicalRecords.MedicalRecord;
import app.domain.services.medicalRecordsServices.MedicalRecordService;

import org.springframework.stereotype.Component;

@Component
public class MedicalRecordUseCase {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordUseCase(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    public void createMedicalRecord(MedicalRecord record) throws Exception {
        medicalRecordService.create(record);
    }

    // Actualmente el servicio expone la búsqueda por documento
    public MedicalRecord getMedicalRecordByDocument(String document) throws Exception {
        return medicalRecordService.getRecord(document).get();
    }

    public void deleteMedicalRecord(Long id) throws Exception {
        medicalRecordService.delete(id);
    }
}
