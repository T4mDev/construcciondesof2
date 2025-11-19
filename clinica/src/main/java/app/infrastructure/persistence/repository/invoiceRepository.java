package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    Optional<InvoiceEntity> findByInvoiceCode(String invoiceCode);

    Optional<InvoiceEntity> findByDocument(String document);
}
