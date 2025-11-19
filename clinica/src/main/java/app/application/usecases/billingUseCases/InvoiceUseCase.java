package app.application.usecases.billingUseCases;

import app.domain.model.billing.Invoice;
import app.domain.services.billingServices.InvoiceService;

import org.springframework.stereotype.Component;

@Component
public class InvoiceUseCase {

    private final InvoiceService invoiceService;

    public InvoiceUseCase(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public void createInvoice(Invoice invoice) throws Exception {
        invoiceService.create(invoice);
    }
}
