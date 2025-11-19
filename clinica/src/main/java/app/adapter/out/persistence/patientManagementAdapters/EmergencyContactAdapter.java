package app.adapter.out.persistence.patientManagementAdapters;

import app.domain.model.patienManagement.EmergencyContact;
import app.domain.ports.patienManagementPorts.EmergencyContactPort;
import app.infrastructure.persistence.entities.EmergencyContactEntity;
import app.infrastructure.persistence.repository.EmergencyContactRepository;
import app.infrastructure.persistence.mapper.EmergencyContactMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmergencyContactAdapter implements EmergencyContactPort {

    private final EmergencyContactRepository repository;

    public EmergencyContactAdapter(EmergencyContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<EmergencyContact> findById(Long id) {
        return repository.findById(id)
            .map(EmergencyContactMapper::toDomain);
    }

    @Override
    public Optional<EmergencyContact> findByPatientDocument(String patientDocument) {
        return repository.findByPatientDocument(patientDocument)
                .map(EmergencyContactMapper::toDomain);
    }

    @Override
    public List<EmergencyContact> findAll() {
        return repository.findAll()
                .stream()
                .map(EmergencyContactMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public EmergencyContact save(EmergencyContact contact) {
        EmergencyContactEntity entity = EmergencyContactMapper.toEntity(contact);
        EmergencyContactEntity saved = repository.save(entity);
        return EmergencyContactMapper.toDomain(saved);
    }

    @Override
    public void delete(EmergencyContact contact) {
        repository.deleteById(contact.getId());
    }

}
