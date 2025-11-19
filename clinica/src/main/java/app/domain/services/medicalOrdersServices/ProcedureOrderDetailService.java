package app.domain.services.medicalOrdersServices;

import org.springframework.stereotype.Service;

import app.domain.model.medicalOrders.ProcedureOrderDetail;
import app.domain.model.people.Specialty;
import app.domain.ports.medicalOrdersPorts.ProcedureOrderDetailPort;

/**
 * Servicio de dominio para la gestión de detalles de órdenes de procedimiento.
 * Encapsula las reglas de negocio relacionadas con la creación, validación,
 * eliminación y asignación de especialistas en procedimientos médicos,
 * delegando la persistencia en {@link ProcedureOrderDetailPort}.
 */
@Service
public class ProcedureOrderDetailService {

    private final ProcedureOrderDetailPort procedureOrderDetailPort;

    /**
     * Constructor que inyecta el puerto de persistencia de detalles de
     * procedimientos.
     * 
     * @param procedureOrderDetailPort instancia de {@link ProcedureOrderDetailPort}
     */
    public ProcedureOrderDetailService(ProcedureOrderDetailPort procedureOrderDetailPort) {
        this.procedureOrderDetailPort = procedureOrderDetailPort;
    }

    /**
     * Crea un nuevo detalle de procedimiento.
     * Reglas de negocio:
     * <ul>
     * <li>Si el procedimiento requiere especialista, debe asignarse una
     * especialidad.</li>
     * <li>No puede existir otro detalle con el mismo ID.</li>
     * <li>El número de ítem es obligatorio.</li>
     * <li>La cantidad debe ser mayor a cero.</li>
     * <li>La frecuencia es obligatoria.</li>
     * </ul>
     * 
     * @param detail objeto {@link ProcedureOrderDetail} a crear
     * @throws Exception si alguna regla de negocio es violada
     */
    public void create(ProcedureOrderDetail detail) throws Exception {
        if (detail == null) {
            throw new Exception("Detalle de procedimiento inválido");
        }

        validateSpecialistRequirement(detail);

        ProcedureOrderDetail existing = procedureOrderDetailPort.findById(detail.getId());
        if (existing != null) {
            throw new Exception("Ya existe un detalle de procedimiento con ese ID");
        }

        if (detail.getItemNumber() == null || detail.getItemNumber().isEmpty()) {
            throw new Exception("El número de ítem es obligatorio");
        }

        if (detail.getQuantity() <= 0) {
            throw new Exception("La cantidad debe ser mayor a cero");
        }

        if (detail.getFrequency() == null || detail.getFrequency().isEmpty()) {
            throw new Exception("La frecuencia es obligatoria");
        }

        procedureOrderDetailPort.save(detail);
    }

    /**
     * Valida que un procedimiento que requiere especialista tenga asignada una
     * especialidad.
     * Regla de negocio: si {@link ProcedureOrderDetail#isRequiresSpecialist()} es
     * true,
     * debe existir un tipo de especialista asignado.
     * 
     * @param detail objeto {@link ProcedureOrderDetail} a validar
     * @throws Exception si el procedimiento requiere especialista pero no se asignó
     *                   ninguno
     */
    public void validateSpecialistRequirement(ProcedureOrderDetail detail) throws Exception {
        if (detail.isRequiresSpecialist() && detail.getSpecialistTypeId() == null) {
            throw new Exception("El procedimiento requiere una especialidad, pero no se ha asignado ninguna");
        }
    }

    /**
     * Elimina un detalle de procedimiento existente.
     * Regla de negocio: el detalle debe existir en el sistema.
     * 
     * @param detail objeto {@link ProcedureOrderDetail} a eliminar
     * @throws Exception si el detalle no existe
     */
    public void delete(ProcedureOrderDetail detail) throws Exception {
        ProcedureOrderDetail existing = procedureOrderDetailPort.findById(detail.getId());
        if (existing == null) {
            throw new Exception("No se encontró el detalle de procedimiento para eliminar");
        }

        procedureOrderDetailPort.delete(detail);
    }

    /**
     * Asigna una especialidad a un detalle de procedimiento.
     * Reglas de negocio:
     * <ul>
     * <li>El detalle debe existir en el sistema.</li>
     * <li>El procedimiento debe requerir especialista.</li>
     * </ul>
     * 
     * @param detail    objeto {@link ProcedureOrderDetail} al que se asignará la
     *                  especialidad
     * @param specialty objeto {@link Specialty} que representa la especialidad
     * @throws Exception si el detalle no existe o el procedimiento no requiere
     *                   especialista
     */
    public void assignSpecialist(ProcedureOrderDetail detail, Specialty specialty) throws Exception {
        ProcedureOrderDetail existing = procedureOrderDetailPort.findById(detail.getId());
        if (existing == null) {
            throw new Exception("No se encontró el detalle de procedimiento para asignar especialidad");
        }

        if (!existing.isRequiresSpecialist()) {
            throw new Exception("Este procedimiento no requiere especialidad");
        }

        if (specialty == null || specialty.getId() == null) {
            throw new Exception("Especialidad inválida para asignar");
        }

        existing.setSpecialistTypeId(specialty.getId());
        procedureOrderDetailPort.save(existing);
    }
}
