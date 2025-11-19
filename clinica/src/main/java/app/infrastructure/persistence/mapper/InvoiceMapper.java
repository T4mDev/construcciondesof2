package app.infrastructure.persistence.mapper;

import app.domain.model.billing.Invoice;
import app.infrastructure.persistence.entities.InvoiceEntity;

public class InvoiceMapper {

    public static InvoiceEntity toEntity(Invoice invoice) {
        if (invoice == null)
            return null;
        InvoiceEntity entity = new InvoiceEntity();
        entity.setId(invoice.getId());
        entity.setAmount(invoice.getAmount());
        entity.setDescription(invoice.getDescription());
        entity.setInvoiceCode(invoice.getInvoiceCode());
        return entity;
    }

    public static Invoice toDomain(InvoiceEntity entity) {
        if (entity == null)
            return null;
        Invoice invoice = new Invoice();
        invoice.setId(entity.getId());
        invoice.setAmount(entity.getAmount());
        invoice.setDescription(entity.getDescription());
        invoice.setInvoiceCode(entity.getInvoiceCode());
        return invoice;
    }
}
