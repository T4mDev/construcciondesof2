package app.adapter.out.persistence.medicalOrderAdapters;

import app.domain.model.medicalOrders.DiagnosticHelp;
import app.domain.ports.medicalOrdersPorts.DiagnosticHelpPort;
import app.infrastructure.persistence.entities.DiagnosticHelpEntity;
import app.infrastructure.persistence.mapper.DiagnosticHelpMapper;
import app.infrastructure.persistence.repository.DiagnosticHelpRepository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DiagnosticHelpAdapter implements DiagnosticHelpPort {

    private final DiagnosticHelpRepository repository;
    private final DiagnosticHelpMapper mapper;

    public DiagnosticHelpAdapter(DiagnosticHelpRepository repository, DiagnosticHelpMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void save(DiagnosticHelp diagnosticHelp) throws Exception {
        DiagnosticHelpEntity entity = mapper.toEntity(diagnosticHelp);
        repository.save(entity);
    }

    @Override
    public DiagnosticHelp findById(String id) throws Exception {
        Long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new Exception("ID inválido: debe ser numérico");
        }

        Optional<DiagnosticHelpEntity> entity = repository.findById(longId);
        return entity.map(mapper::toDomain)
                .orElseThrow(() -> new Exception("No se encontró DiagnosticHelp con id=" + id));
    }

    public DiagnosticHelp findByItemNumber(String itemNumber) {
        Optional<DiagnosticHelpEntity> entity = repository.findByItemNumber(itemNumber);
        return entity.map(mapper::toDomain).orElse(null);
    }

    @Override
    public void delete(DiagnosticHelp diagnosticHelp) throws Exception {
        if (diagnosticHelp == null || diagnosticHelp.getId() == null) {
            throw new Exception("DiagnosticHelp o id nulo: imposible eliminar");
        }
        repository.deleteById(diagnosticHelp.getId());
    }

    public List<DiagnosticHelp> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return repository.count() == 0;
    }
}
