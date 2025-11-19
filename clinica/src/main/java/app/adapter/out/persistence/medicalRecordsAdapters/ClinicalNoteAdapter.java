package app.adapter.out.persistence.medicalRecordsAdapters;

import app.domain.model.medicalRecords.ClinicalNote;
import app.domain.ports.medicalRecordsPorts.ClinicalNotePort;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapter de salida para la entidad {@link ClinicalNote}.
 *
 * Implementa el puerto {@link ClinicalNotePort} simulando una base de datos en
 * memoria
 * mediante un mapa. Permite realizar operaciones CRUD básicas sobre notas
 * médicas
 * asociadas a un paciente.
 */
@Repository
public class ClinicalNoteAdapter implements ClinicalNotePort {

    // Simulación de base de datos en memoria
    private final Map<Long, ClinicalNote> database = new HashMap<>();

    /**
     * Busca una nota médica en la base de datos por el id del paciente.
     *
     * @param note Nota médica con referencia al paciente
     * @return La nota médica encontrada o null si no existe
     */
    @Override
    public ClinicalNote findById(ClinicalNote note) {
        if (note.getPatient() == null)
            return null;
        long patientId = note.getPatient().getId();
        return database.get(patientId);
    }

    /**
     * Guarda una nota médica en la base de datos.
     * El id del paciente se usa como clave.
     *
     * @param note Nota médica a guardar
     */
    @Override
    public void save(ClinicalNote note) {
        long patientId = note.getPatient().getId();
        database.put(patientId, note);
    }

    /**
     * Elimina una nota médica de la base de datos usando el id del paciente.
     *
     * @param note Nota médica a eliminar
     */
    @Override
    public void delete(ClinicalNote note) {
        long patientId = note.getPatient().getId();
        database.remove(patientId);
    }
}
