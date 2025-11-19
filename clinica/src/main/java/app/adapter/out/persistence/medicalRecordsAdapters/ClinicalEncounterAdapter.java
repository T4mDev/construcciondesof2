package app.adapter.out.persistence.medicalRecordsAdapters;

import app.domain.model.medicalRecords.ClinicalEncounter;
import app.domain.ports.medicalRecordsPorts.ClinicalEncounterPort;
import app.infrastructure.persistence.entities.ClinicalEncounterEntity;
import app.infrastructure.persistence.mapper.ClinicalEncounterMapper;
import app.infrastructure.persistence.repository.ClinicalEncounterRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ClinicalEncounterAdapter implements ClinicalEncounterPort {

    private final ClinicalEncounterRepository repository;

    public ClinicalEncounterAdapter(ClinicalEncounterRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClinicalEncounter findByPatientId(String patientDocument) throws Exception {
        Optional<ClinicalEncounterEntity> found = repository.findAll()
                .stream()
                .filter(e -> e.getPatientDocument() != null && e.getPatientDocument().equals(patientDocument))
                .findFirst();

        return found.map(ClinicalEncounterMapper::toDomain)
                .orElseThrow(() -> new Exception("ClinicalEncounter not found for patientDocument: " + patientDocument));
    }

    @Override
    public void save(ClinicalEncounter encounter) throws Exception {
        if (encounter == null) throw new Exception("ClinicalEncounter is null");
        ClinicalEncounterEntity entity = ClinicalEncounterMapper.toEntity(encounter);
        repository.save(entity);
    }

    @Override
    public void delete(ClinicalEncounter encounter) throws Exception {
        if (encounter == null) throw new Exception("ClinicalEncounter is null");

        if (encounter.getId() != null) {
            repository.deleteById(encounter.getId());
            return;
        }

        // fallback: try to find by patientDocument and delete
        if (encounter.getPatientDocument() != null) {
            Optional<ClinicalEncounterEntity> found = repository.findAll()
                    .stream()
                    .filter(e -> encounter.getPatientDocument().equals(e.getPatientDocument()))
                    .findFirst();

            if (found.isPresent()) {
                repository.delete(found.get());
                return;
            }
        }

        throw new Exception("ClinicalEncounter to delete not found");
    }
}
