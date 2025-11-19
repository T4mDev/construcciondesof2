package app.domain.services.medicalOrdersServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.medicalOrders.DiagnosticHelp;
import app.domain.ports.medicalOrdersPorts.DiagnosticHelpPort;

/**
 * Servicio de dominio para la gestión de ítems de ayuda diagnóstica.
 * Encapsula las reglas de negocio relacionadas con la creación, actualización
 * de disponibilidad de resultados, eliminación y validación de ítems
 * diagnósticos,
 * delegando la persistencia en {@link DiagnosticHelpPort}.
 */
@Service
public class DiagnosticHelpService {

    @Autowired
    private final DiagnosticHelpPort diagnosticHelpPort;

    /**
     * Constructor que inyecta el puerto de persistencia de ítems diagnósticos.
     * 
     * @param diagnosticHelpPort instancia de {@link DiagnosticHelpPort}
     */
    public DiagnosticHelpService(DiagnosticHelpPort diagnosticHelpPort) {
        this.diagnosticHelpPort = diagnosticHelpPort;
    }

    /**
     * Crea un nuevo ítem de ayuda diagnóstica.
     * Reglas de negocio:
     * <ul>
     * <li>El ítem debe ser válido (ID, número y nombre obligatorios).</li>
     * <li>No puede existir otro ítem con el mismo ID.</li>
     * </ul>
     * 
     * @param item objeto {@link DiagnosticHelp} a crear
     * @throws Exception si el ítem no es válido o ya existe con el mismo ID
     */
    public void create(DiagnosticHelp item) throws Exception {
        validateDiagnosticHelp(item);

        DiagnosticHelp existing = diagnosticHelpPort.findById(String.valueOf(item.getId()));
        if (existing != null) {
            throw new Exception("Ya existe un ítem diagnóstico con ese ID");
        }

        diagnosticHelpPort.save(item);
    }

    /**
     * Marca un ítem diagnóstico como disponible para resultados.
     * Regla de negocio: el ítem debe existir en el sistema.
     * 
     * @param itemId identificador del ítem
     * @throws Exception si el ítem no existe
     */
    public void markResultAvailable(String itemId) throws Exception {
        DiagnosticHelp item = diagnosticHelpPort.findById(itemId);
        if (item == null) {
            throw new Exception("No se encontró el ítem diagnóstico");
        }

        item.setResultAvailable(true);
        diagnosticHelpPort.save(item);
    }

    /**
     * Elimina un ítem diagnóstico existente.
     * Regla de negocio: el ítem debe existir en el sistema.
     * 
     * @param item objeto {@link DiagnosticHelp} a eliminar
     * @throws Exception si el ítem no existe
     */
    public void delete(DiagnosticHelp item) throws Exception {
        DiagnosticHelp existing = diagnosticHelpPort.findById(String.valueOf(item.getId()));
        if (existing == null) {
            throw new Exception("No se encontró el ítem para eliminar");
        }

        diagnosticHelpPort.delete(item);
    }

    /**
     * Valida los datos de un ítem diagnóstico.
     * Reglas de negocio:
     * <ul>
     * <li>El ID del ítem es obligatorio.</li>
     * <li>El número de ítem es obligatorio.</li>
     * <li>El nombre del ítem es obligatorio.</li>
     * </ul>
     * 
     * @param item objeto {@link DiagnosticHelp} a validar
     * @throws Exception si alguna regla de negocio es violada
     */
    public void validateDiagnosticHelp(DiagnosticHelp item) throws Exception {
        if (item.getId() == null) {
            throw new Exception("El ID del ítem es obligatorio");
        }

        if (item.getItemNumber() == null || item.getItemNumber().isEmpty()) {
            throw new Exception("El número de ítem es obligatorio");
        }

        if (item.getName() == null || item.getName().isEmpty()) {
            throw new Exception("El nombre del ítem es obligatorio");
        }
    }
}
