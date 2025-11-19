package app.domain.services.patientManagementServices;

import app.domain.model.patienManagement.Patient;
import app.domain.ports.patienManagementPorts.PatientPort;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio para la gestión de pacientes.
 * Encapsula las reglas de negocio relacionadas con la creación, búsqueda,
 * actualización de información de contacto y eliminación de pacientes,
 * delegando la persistencia en {@link PatientPort}.
 */
@Service
public class PatientService {

    private final PatientPort patientPort;

    public PatientService(PatientPort patientPort) {
        this.patientPort = patientPort;
    }

    /**
     * Crea un nuevo paciente en el sistema.
     */
    public void create(Patient patient) throws Exception {
        validatePatientData(patient);

        // Verifica si el documento ya existe
        if (patientPort.findByDocument(patient.getDocument()).isPresent()) {
            throw new Exception("Ya existe un paciente con ese documento");
        }

        patientPort.save(patient);
    }

    /**
     * Busca un paciente por documento.
     */
    public Patient findByDocument(String document) throws Exception {
        return patientPort.findByDocument(document)
                .orElseThrow(() -> new Exception("No se encontró paciente con ese documento"));
    }

    /**
     * Actualiza datos de contacto.
     */
    public void updateContactInfo(Patient patient) throws Exception {
        Optional<Patient> optExisting = patientPort.findByDocument(patient.getDocument());

        if (optExisting.isEmpty()) {
            throw new Exception("No se encontró paciente para actualizar");
        }

        Patient existing = optExisting.get();

        // Valida teléfono
        if (patient.getPhone() == null || !patient.getPhone().matches("\\d{10}")) {
            throw new Exception("El número de teléfono debe tener 10 dígitos");
        }

        // Valida dirección
        if (patient.getAddress() == null || patient.getAddress().length() > 30) {
            throw new Exception("La dirección debe tener máximo 30 caracteres");
        }

        existing.setPhone(patient.getPhone());
        existing.setAddress(patient.getAddress());

        patientPort.save(existing);
    }

    /**
     * Elimina un paciente por documento.
     */
    public void delete(String document) throws Exception {
        Patient existing = patientPort.findByDocument(document)
                .orElseThrow(() -> new Exception("No se encontró paciente para eliminar"));

        patientPort.delete(existing.getId());
    }

    /**
     * Validaciones de negocio.
     */
    private void validatePatientData(Patient patient) throws Exception {
        if (patient.getDocument() == null || patient.getDocument().isEmpty()) {
            throw new Exception("El documento es obligatorio");
        }

        if (patient.getFullName() == null || patient.getFullName().isEmpty()) {
            throw new Exception("El nombre completo es obligatorio");
        }

        if (patient.getBirthDate() == null || calculateAge(patient.getBirthDate()) > 150) {
            throw new Exception("Fecha de nacimiento inválida");
        }

        if (patient.getPhone() == null || !patient.getPhone().matches("\\d{10}")) {
            throw new Exception("El número de teléfono debe tener 10 dígitos");
        }

        if (patient.getAddress() == null || patient.getAddress().length() > 30) {
            throw new Exception("La dirección debe tener máximo 30 caracteres");
        }

        if (patient.getGender() == null) {
            throw new Exception("El género es obligatorio");
        }

        if (patient.getDocument() == null || patient.getDocument().isEmpty()) {
            throw new Exception("El documento es obligatorio");
        }
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
