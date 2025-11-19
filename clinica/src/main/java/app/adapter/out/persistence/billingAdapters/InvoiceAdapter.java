package app.adapter.out.persistence.billingAdapters;

import app.domain.model.billing.Invoice;
import app.domain.ports.billingPorts.InvoicePort;
import app.infrastructure.persistence.repository.InvoiceRepository;
import app.infrastructure.persistence.entities.InvoiceEntity;

import java.util.Optional;
import java.util.List;
 

import org.springframework.stereotype.Component;

@Component
public class InvoiceAdapter implements InvoicePort {

    private final InvoiceRepository repository;

    public InvoiceAdapter(InvoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Invoice> findByInvoiceCode(String invoiceCode) {
        return repository.findByInvoiceCode(invoiceCode).map(this::toDomain);
    }

    @Override
    public List<Invoice> findByPatientDocument(String document) {
        return repository.findByDocument(document)
                .map(this::toDomain)
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public Invoice save(Invoice invoice) {
        InvoiceEntity entity = toEntity(invoice);
        InvoiceEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }

    // --- Conversiones entre entidad y dominio ---
    private Invoice toDomain(InvoiceEntity entity) {
        Invoice invoice = new Invoice();
        invoice.setId(entity.getId());
        invoice.setAmount(entity.getAmount());
        invoice.setDescription(entity.getDescription());
        if (entity.getDateTime() != null) {
            invoice.setIssueDate(entity.getDateTime().toLocalDate());
        }
        invoice.setInvoiceCode(entity.getInvoiceCode());

        // Aquí solo guardamos los datos simples de Patient y MedicalInsurance
        // Puedes enlazar objetos completos si quieres más detalle
        return invoice;
    }

    private InvoiceEntity toEntity(Invoice invoice) {
        InvoiceEntity entity = new InvoiceEntity();
        entity.setId(invoice.getId());
        entity.setAmount(invoice.getAmount());
        entity.setDescription(invoice.getDescription());
        // map issueDate (LocalDate) to dateTime (LocalDateTime) at start of day if present
        if (invoice.getIssueDate() != null) {
            entity.setDateTime(invoice.getIssueDate().atStartOfDay());
        }
        entity.setInvoiceCode(invoice.getInvoiceCode());

        // Guardamos solo identificadores simples
        if (invoice.getPatient() != null) {
            entity.setDocument(invoice.getPatient().getDocument());
        }
        if (invoice.getMedicalInsurance() != null) {
            entity.setMedicalInsurance(invoice.getMedicalInsurance().getPolicyNumber());
        }

        return entity;
    }
}
