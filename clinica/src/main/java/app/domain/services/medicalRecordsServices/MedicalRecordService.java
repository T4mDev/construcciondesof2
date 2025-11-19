package app.domain.services.medicalRecordsServices;

import app.domain.model.medicalRecords.MedicalRecord;
import app.domain.ports.medicalRecordsPorts.MedicalRecordPort;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    private final MedicalRecordPort port;

    public MedicalRecordService(MedicalRecordPort port) {
        this.port = port;
    }

    public MedicalRecord create(MedicalRecord record) throws Exception {
        if (record == null) {
            throw new Exception("Historia clínica inválida");
        }

        if (record.getPatientDocument() == null || record.getPatientDocument().isEmpty())
            throw new Exception("El documento del paciente es obligatorio");

        Optional<MedicalRecord> existing = port.findByPatientDocument(record.getPatientDocument());
        if (existing.isPresent()) {
            throw new Exception("Ya existe una historia clínica con ese documento");
        }

        return port.save(record);
    }

    public Optional<MedicalRecord> getRecord(String patientDocument) throws Exception {
        Optional<MedicalRecord> record = port.findByPatientDocument(patientDocument);
        if (record.isEmpty()) {
            throw new Exception("No existe una historia clínica con ese documento");
        }
        return record;
    }

    public void delete(Long id) throws Exception {
        if (port.findById(id).isEmpty())
            throw new Exception("No existe la historia clínica para eliminar");
        port.delete(id);
    }

}
