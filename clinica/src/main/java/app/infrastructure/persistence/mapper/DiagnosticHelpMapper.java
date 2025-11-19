package app.infrastructure.persistence.mapper;

import app.domain.model.medicalOrders.DiagnosticHelp;
import app.infrastructure.persistence.entities.DiagnosticHelpEntity;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticHelpMapper {

    public DiagnosticHelp toDomain(DiagnosticHelpEntity entity) {
        if (entity == null)
            return null;

        DiagnosticHelp domain = new DiagnosticHelp();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setResultAvailable(entity.isResultAvailable());
        domain.setItemNumber(entity.getItemNumber());
        domain.setDiagnosticDetails(entity.getDiagnosisDetails());
        return domain;
    }

    public DiagnosticHelpEntity toEntity(DiagnosticHelp domain) {
        if (domain == null)
            return null;

        DiagnosticHelpEntity entity = new DiagnosticHelpEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setResultAvailable(domain.isResultAvailable());
        entity.setItemNumber(domain.getItemNumber());
        entity.setDiagnosisDetails(domain.getDiagnosticDetails());
        return entity;
    }
}
