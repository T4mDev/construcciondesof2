package app.application.usecases.medicalOrderUseCases;

import org.springframework.stereotype.Component;

import app.domain.model.medicalOrders.DiagnosticHelp;
import app.domain.services.medicalOrdersServices.DiagnosticHelpService;

@Component
public class DiagnosticHelpUseCase {

    private final DiagnosticHelpService diagnosticHelpService;

    public DiagnosticHelpUseCase(DiagnosticHelpService diagnosticHelpService) {
        this.diagnosticHelpService = diagnosticHelpService;
    }

    public void createDiagnosticHelp(DiagnosticHelp item) throws Exception {
        diagnosticHelpService.create(item);
    }

    public void markResultAvailable(String itemId) throws Exception {
        diagnosticHelpService.markResultAvailable(itemId);
    }

    public void deleteDiagnosticHelp(DiagnosticHelp item) throws Exception {
        diagnosticHelpService.delete(item);
    }

    public void validateDiagnosticHelp(DiagnosticHelp item) throws Exception {
        diagnosticHelpService.validateDiagnosticHelp(item);
    }
}
