package app.domain.ports.medicalOrdersPorts;

import app.domain.model.medicalOrders.DiagnosticHelp;

public interface DiagnosticHelpPort {
    DiagnosticHelp findById(String id) throws Exception;

    void save(DiagnosticHelp item) throws Exception;

    void delete(DiagnosticHelp item) throws Exception;
}
