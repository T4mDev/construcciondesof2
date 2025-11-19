package app.domain.ports.billingPorts;

import java.util.List;
import java.util.Optional;

import app.domain.model.billing.Invoice;

public interface InvoicePort {
    Optional<Invoice> findById(Long id);

    Optional<Invoice> findByInvoiceCode(String invoiceCode);

    List<Invoice> findByPatientDocument(String document);

    Invoice save(Invoice invoice);

    void delete(Long id);

    boolean isEmpty();
}
