package app.domain.services.medicalOrdersServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.medicalOrders.Prescription;
import app.domain.ports.medicalOrdersPorts.PrescriptionPort;

/**
 * Servicio de dominio para la gestión de prescripciones médicas.
 * Encapsula las reglas de negocio relacionadas con la creación, validación,
 * eliminación y actualización de prescripciones, delegando la persistencia
 * en {@link PrescriptionPort}.
 */
@Service
public class PrescriptionService {

    @Autowired
    private final PrescriptionPort prescriptionPort;

    /**
     * Constructor que inyecta el puerto de persistencia de prescripciones.
     * 
     * @param prescriptionPort instancia de {@link PrescriptionPort}
     */
    public PrescriptionService(PrescriptionPort prescriptionPort) {
        this.prescriptionPort = prescriptionPort;
    }

    /**
     * Crea una nueva prescripción médica.
     * Reglas de negocio:
     * <ul>
     * <li>La dosis del medicamento es obligatoria.</li>
     * <li>La duración del tratamiento debe ser mayor a cero.</li>
     * <li>El código del ítem es obligatorio.</li>
     * <li>No puede existir otra prescripción con el mismo ID.</li>
     * </ul>
     * 
     * @param prescription objeto {@link Prescription} a crear
     * @throws Exception si alguna regla de negocio es violada o ya existe la
     *                   prescripción
     */
    public void create(Prescription prescription) throws Exception {
        validateDosage(prescription);

        Prescription existing = prescriptionPort.findById(prescription.getId());
        if (existing != null) {
            throw new Exception("Ya existe una prescripción con ese ID");
        }

        prescriptionPort.save(prescription);
    }

    /**
     * Valida que la prescripción cumpla con las reglas de negocio.
     * Reglas de negocio:
     * <ul>
     * <li>La dosis del medicamento es obligatoria.</li>
     * <li>La duración del tratamiento debe ser mayor a cero.</li>
     * <li>El código del ítem es obligatorio.</li>
     * </ul>
     * 
     * @param prescription objeto {@link Prescription} a validar
     * @throws Exception si alguna regla de negocio es violada
     */
    public void validateDosage(Prescription prescription) throws Exception {
        if (prescription.getDose() == null || prescription.getDose().isEmpty()) {
            throw new Exception("La dosis del medicamento es obligatoria");
        }

        if (prescription.getDurationDays() <= 0) {
            throw new Exception("La duración del tratamiento debe ser mayor a cero");
        }

        if (prescription.getItemCode() == null || prescription.getItemCode().isEmpty()) {
            throw new Exception("El código del ítem es obligatorio");
        }
    }

    /**
     * Elimina una prescripción médica existente.
     * Regla de negocio: la prescripción debe existir en el sistema.
     * 
     * @param prescription objeto {@link Prescription} a eliminar
     * @throws Exception si la prescripción no existe
     */
    public void delete(Prescription prescription) throws Exception {
        Prescription existing = prescriptionPort.findById(prescription.getId());
        if (existing == null) {
            throw new Exception("No se encontró la prescripción para eliminar");
        }

        prescriptionPort.delete(prescription);
    }

    /**
     * Actualiza la duración de una prescripción médica existente.
     * Reglas de negocio:
     * <ul>
     * <li>La nueva duración debe ser mayor a cero.</li>
     * <li>La prescripción debe existir en el sistema.</li>
     * </ul>
     * 
     * @param prescription objeto {@link Prescription} a actualizar
     * @param newDays      nueva duración en días
     * @throws Exception si la prescripción no existe o la nueva duración es
     *                   inválida
     */
    public void updateDuration(Prescription prescription, int newDays) throws Exception {
        if (newDays <= 0) {
            throw new Exception("La nueva duración debe ser mayor a cero");
        }

        Prescription existing = prescriptionPort.findById(prescription.getId());
        if (existing == null) {
            throw new Exception("No se encontró la prescripción para actualizar");
        }

        existing.setDurationDays(newDays);
        prescriptionPort.save(existing);
    }
}
