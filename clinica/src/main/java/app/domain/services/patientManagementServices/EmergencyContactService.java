package app.domain.services.patientManagementServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.patienManagement.EmergencyContact;
import app.domain.ports.patienManagementPorts.EmergencyContactPort;

/**
 * Servicio de dominio para la gestión de contactos de emergencia.
 * Encapsula las reglas de negocio relacionadas con la creación, actualización,
 * búsqueda y eliminación de contactos de emergencia, delegando la persistencia
 * en {@link EmergencyContactPort}.
 */
@Service
public class EmergencyContactService {

    @Autowired
    private final EmergencyContactPort emergencyContactPort;

    /**
     * Constructor que inyecta el puerto de persistencia de contactos de emergencia.
     * 
     * @param emergencyContactPort instancia de {@link EmergencyContactPort}
     */
    public EmergencyContactService(EmergencyContactPort emergencyContactPort) {
        this.emergencyContactPort = emergencyContactPort;
    }

    /**
     * Crea un nuevo contacto de emergencia para un paciente.
     * Reglas de negocio:
     * <ul>
     * <li>Un paciente solo puede tener un contacto de emergencia.</li>
     * <li>El número de teléfono debe ser válido (10 dígitos numéricos).</li>
     * </ul>
     * 
     * @param contact         objeto {@link EmergencyContact} a crear
     * @param patientDocument documento del paciente
     * @throws Exception si ya existe un contacto o el teléfono es inválido
     */
    public void create(EmergencyContact contact, String patientDocument) throws Exception {
        var existingOpt = emergencyContactPort.findByPatientDocument(patientDocument);
        if (existingOpt.isPresent()) {
            throw new Exception("Ya existe un contacto de emergencia para este paciente");
        }

        validatePhone(contact.getPhone());
        contact.setPatientDocument(patientDocument);
        emergencyContactPort.save(contact);
    }

    /**
     * Actualiza un contacto de emergencia existente.
     * Reglas de negocio:
     * <ul>
     * <li>El contacto debe existir en el sistema.</li>
     * <li>El número de teléfono debe ser válido (10 dígitos numéricos).</li>
     * </ul>
     * 
     * @param contact         objeto {@link EmergencyContact} con los nuevos datos
     * @param patientDocument documento del paciente
     * @throws Exception si el contacto no existe o el teléfono es inválido
     */
    public void update(EmergencyContact contact, String patientDocument) throws Exception {
        var existingOpt = emergencyContactPort.findByPatientDocument(patientDocument);
        if (existingOpt.isEmpty()) {
            throw new Exception("No se encontró contacto de emergencia para actualizar");
        }

        validatePhone(contact.getPhone());
        contact.setPatientDocument(patientDocument);
        // preserve id if not provided
        if (contact.getId() == null) {
            contact.setId(existingOpt.get().getId());
        }
        emergencyContactPort.save(contact);
    }

    /**
     * Busca un contacto de emergencia por documento del paciente.
     * Regla de negocio: el contacto debe existir en el sistema.
     * 
     * @param patientDocument documento del paciente
     * @return contacto de emergencia encontrado
     * @throws Exception si no se encuentra el contacto
     */
    public EmergencyContact findByPatient(String patientDocument) throws Exception {
        var contactOpt = emergencyContactPort.findByPatientDocument(patientDocument);
        if (contactOpt.isEmpty()) {
            throw new Exception("No se encontró contacto de emergencia para este paciente");
        }
        return contactOpt.get();
    }

    /**
     * Elimina un contacto de emergencia existente.
     * Regla de negocio: el contacto debe existir en el sistema.
     * 
     * @param patientDocument documento del paciente
     * @throws Exception si no se encuentra el contacto
     */
    public void delete(String patientDocument) throws Exception {
        var contactOpt = emergencyContactPort.findByPatientDocument(patientDocument);
        if (contactOpt.isEmpty()) {
            throw new Exception("No se encontró contacto de emergencia para eliminar");
        }
        emergencyContactPort.delete(contactOpt.get());
    }

    /**
     * Valida el número de teléfono de un contacto de emergencia.
     * Regla de negocio: debe tener exactamente 10 dígitos numéricos.
     * 
     * @param phone número de teléfono
     * @throws Exception si el número no cumple con la regla
     */
    private void validatePhone(String phone) throws Exception {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new Exception("El número de teléfono debe tener exactamente 10 dígitos numéricos");
        }
    }
}
