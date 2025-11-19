package app.domain.services.billingServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.billing.Invoice;
import app.domain.ports.billingPorts.InvoicePort;
import java.util.Optional;

/**
 * Servicio de dominio para la gestión de facturas.
 * Encapsula las reglas de negocio relacionadas con la creación de facturas,
 * delegando la persistencia en {@link InvoicePort}.
 */
@Service
public class InvoiceService {

    @Autowired
    private final InvoicePort invoicePort;

    /**
     * Constructor que inyecta el puerto de persistencia de facturas.
     * 
     * @param invoicePort instancia de {@link InvoicePort}
     */
    public InvoiceService(InvoicePort invoicePort) {
        this.invoicePort = invoicePort;
    }

    /**
     * Crea una nueva factura en el sistema.
     * Reglas de negocio:
     * <ul>
     * <li>No puede existir otra factura con el mismo ID.</li>
     * </ul>
     * 
     * @param invoice objeto {@link Invoice} a crear
     * @throws Exception si ya existe una factura con el mismo ID
     */
    public void create(Invoice invoice) throws Exception {
        // Comprobar por id si viene establecido (id > 0)
        if (invoice.getId() > 0) {
            Optional<Invoice> existing = invoicePort.findById(invoice.getId());
            if (existing.isPresent()) {
                throw new Exception("Ya existe una factura registrada con ese ID");
            }
        }

        // Comprobar por código de factura si está presente
        if (invoice.getInvoiceCode() != null && !invoice.getInvoiceCode().isEmpty()) {
            Optional<Invoice> existingByCode = invoicePort.findByInvoiceCode(invoice.getInvoiceCode());
            if (existingByCode.isPresent()) {
                throw new Exception("Ya existe una factura registrada con ese código");
            }
        }

        invoicePort.save(invoice);
    }
}
