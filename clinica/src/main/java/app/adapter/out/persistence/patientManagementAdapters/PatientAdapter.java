package app.adapter.out.persistence.patientManagementAdapters;

import app.domain.model.patienManagement.Patient;
import app.domain.ports.patienManagementPorts.PatientPort;
import app.infrastructure.persistence.mapper.PatientMapper;
import app.infrastructure.persistence.repository.PatientRepository;
import app.infrastructure.persistence.entities.PatientEntity;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class PatientAdapter implements PatientPort {

    private final PatientRepository repository;

    public PatientAdapter(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Patient> findById(long id) {
        return repository.findById(id)
                .map(PatientMapper::toDomain);
    }

    @Override
    public Optional<Patient> findByDocument(String document) {
        return repository.findByDocument(document)
                .map(PatientMapper::toDomain);
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll()
                .stream()
                .map(PatientMapper::toDomain)
                .toList();
    }

    @Override
    public Patient save(Patient patient) {
        PatientEntity entity = PatientMapper.toEntity(patient);
        PatientEntity saved = repository.save(entity);
        return PatientMapper.toDomain(saved);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }
}
