package app.adapter.out.persistence.medicalRecordsAdapters;

import app.domain.model.medicalRecords.MedicalRecord;
import app.domain.ports.medicalRecordsPorts.MedicalRecordPort;
import app.infrastructure.persistence.mapper.MedicalRecordMapper;
import app.infrastructure.persistence.repository.MedicalRecordRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class MedicalRecordAdapter implements MedicalRecordPort {

    private final MedicalRecordRepository repository;

    public MedicalRecordAdapter(MedicalRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public MedicalRecord save(MedicalRecord record) {
        var entity = MedicalRecordMapper.toEntity(record);
        var saved = repository.save(entity);
        return MedicalRecordMapper.toDomain(saved);
    }

    @Override
    public Optional<MedicalRecord> findById(Long id) {
        return repository.findById(id).map(MedicalRecordMapper::toDomain);
    }

    @Override
    public Optional<MedicalRecord> findByPatientDocument(String doc) {
        return repository.findByPatientDocument(doc).map(MedicalRecordMapper::toDomain);
    }

    @Override
    public List<MedicalRecord> findAll() {
        return repository.findAll()
                .stream()
                .map(MedicalRecordMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }
}
