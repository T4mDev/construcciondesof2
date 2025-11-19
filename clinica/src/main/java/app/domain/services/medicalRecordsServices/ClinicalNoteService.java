package app.domain.services.medicalRecordsServices;

import app.domain.model.medicalRecords.ClinicalNote;
import app.domain.model.patienManagement.Patient;
import app.domain.ports.medicalRecordsPorts.ClinicalNotePort;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio para la gestión de notas clínicas.
 * Encapsula las reglas de negocio relacionadas con la creación, búsqueda,
 * eliminación y actualización de notas clínicas, delegando la persistencia
 * en {@link ClinicalNotePort}.
 */
@Service
public class ClinicalNoteService {

    @Autowired
    private final ClinicalNotePort clinicalNotePort;

    /**
     * Constructor que inyecta el puerto de persistencia de notas clínicas.
     * 
     * @param clinicalNotePort instancia de {@link ClinicalNotePort}
     */
    public ClinicalNoteService(ClinicalNotePort clinicalNotePort) {
        this.clinicalNotePort = clinicalNotePort;
    }

    /**
     * Crea una nueva nota clínica.
     * Reglas de negocio:
     * <ul>
     * <li>La nota debe tener paciente y médico asignados.</li>
     * <li>El contenido de la nota no puede estar vacío.</li>
     * <li>Si no se especifica fecha, se asigna la fecha y hora actual.</li>
     * </ul>
     * 
     * @param note objeto {@link ClinicalNote} a crear
     * @throws Exception si alguna regla de negocio es violada
     */
    public void create(ClinicalNote note) throws Exception {
        if (note.getPatient() == null || note.getDoctor() == null) {
            throw new Exception("La nota clínica debe tener paciente y médico asignados");
        }

        if (note.getReasonOfVisit() == null || note.getReasonOfVisit().isEmpty()) {
            throw new Exception("La razón de la visita no puede estar vacía");
        }

        if (note.getConsultationDateTime() == null) {
            note.setConsultationDateTime(LocalDateTime.now());
        }

        clinicalNotePort.save(note);
    }

    /**
     * Busca una nota clínica por el identificador del paciente.
     * Regla de negocio: la nota clínica debe existir en el sistema.
     * 
     * @param patientId identificador del paciente
     * @return nota clínica encontrada
     * @throws Exception si no se encuentra la nota clínica
     */
    public ClinicalNote findByPatient(long patientId) throws Exception {
        ClinicalNote temp = new ClinicalNote();
        Patient patient = new Patient();
        patient.setId(patientId);
        temp.setPatient(patient);

        ClinicalNote found = clinicalNotePort.findById(temp);
        if (found == null) {
            throw new Exception("No se encontró nota clínica para el paciente");
        }

        return found;
    }

    /**
     * Elimina una nota clínica existente.
     * Regla de negocio: la nota clínica debe existir en el sistema.
     * 
     * @param note objeto {@link ClinicalNote} a eliminar
     * @throws Exception si la nota no existe
     */
    public void delete(ClinicalNote note) throws Exception {
        ClinicalNote existing = clinicalNotePort.findById(note);
        if (existing == null) {
            throw new Exception("No se encontró la nota clínica para eliminar");
        }

        clinicalNotePort.delete(note);
    }

    /**
     * Actualiza el contenido de una nota clínica existente.
     * Reglas de negocio:
     * <ul>
     * <li>La nota clínica debe existir en el sistema.</li>
     * <li>El contenido de la nota no puede estar vacío.</li>
     * <li>La fecha de actualización se asigna automáticamente con la hora
     * actual.</li>
     * </ul>
     * 
     * @param note objeto {@link ClinicalNote} con los nuevos datos
     * @throws Exception si la nota no existe o el contenido es inválido
     */
    public void updateNote(ClinicalNote note) throws Exception {
        ClinicalNote existing = clinicalNotePort.findById(note);
        if (existing == null) {
            throw new Exception("No se encontró la nota clínica para actualizar");
        }

        if (note.getNote() == null || note.getNote().isEmpty()) {
            throw new Exception("El contenido de la nota no puede estar vacío");
        }

        if (note.getConsultationDateTime() == null) {
            note.setConsultationDateTime(LocalDateTime.now());
        }

        clinicalNotePort.save(existing);
    }
}
