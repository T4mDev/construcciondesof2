package app.domain.services.schedulingServices;

import app.domain.model.scheduling.Appointment;
import app.domain.ports.schedulingPorts.AppointmentPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Servicio de dominio para la gestión de citas médicas.
 * Encapsula las reglas de negocio relacionadas con la creación, cancelación,
 * búsqueda y reprogramación de citas, delegando la persistencia en
 * {@link AppointmentPort}.
 */
@Service
public class AppointmentService {

    @Autowired
    private final AppointmentPort appointmentPort;

    /**
     * Constructor que inyecta el puerto de persistencia de citas.
     * 
     * @param appointmentPort instancia de {@link AppointmentPort}
     */
    public AppointmentService(AppointmentPort appointmentPort) {
        this.appointmentPort = appointmentPort;
    }

    /**
     * Crea una nueva cita médica.
     * Reglas de negocio:
     * <ul>
     * <li>La fecha de la cita debe ser futura.</li>
     * <li>La cita debe tener paciente y doctor asignados.</li>
     * </ul>
     * 
     * @param appointment objeto {@link Appointment} a crear
     * @throws Exception si alguna regla de negocio es violada
     */
    public void create(Appointment appointment) throws Exception {
        if (appointment.getDateTime() == null || appointment.getDateTime().isBefore(LocalDateTime.now())) {
            throw new Exception("La fecha de la cita debe ser futura");
        }

        if (appointment.getPatient() == null || appointment.getDoctor() == null) {
            throw new Exception("La cita debe tener paciente y doctor asignados");
        }
        if (appointment.getDoctorDocument() != null) {
            LocalDateTime startRange = appointment.getDateTime().minusMinutes(15);
            LocalDateTime endRange = appointment.getDateTime().plusMinutes(15);

            java.util.List<Appointment> conflictingAppointments = appointmentPort
                    .findByDoctorDocumentAndDateTimeBetween(
                            appointment.getDoctorDocument(), startRange, endRange);

            if (!conflictingAppointments.isEmpty()) {
                throw new Exception("El doctor ya tiene una cita programada en este horario (rango de 15 minutos)");
            }
        }

        appointmentPort.save(appointment);
    }

    /**
     * Cancela una cita médica existente.
     * Regla de negocio: la cita debe existir en el sistema.
     * 
     * @param appointment objeto {@link Appointment} a cancelar
     * @throws Exception si la cita no existe
     */
    public void cancel(Appointment appointment) throws Exception {
        Appointment existing = appointmentPort.findById(appointment);
        if (existing == null) {
            throw new Exception("No se encontró la cita para cancelar");
        }

        appointmentPort.cancel(existing);
    }

    /**
     * Busca una cita médica en el sistema.
     * Regla de negocio: la cita debe existir en el repositorio.
     * 
     * @param appointment objeto {@link Appointment} a buscar
     * @return la cita encontrada
     * @throws Exception si la cita no existe
     */
    public Appointment find(Appointment appointment) throws Exception {
        Appointment found = appointmentPort.findById(appointment);
        if (found == null) {
            throw new Exception("No se encontró la cita");
        }
        return found;
    }

    /**
     * Reprograma una cita médica existente.
     * Reglas de negocio:
     * <ul>
     * <li>La nueva fecha debe ser futura.</li>
     * <li>La cita debe existir en el sistema.</li>
     * </ul>
     * 
     * @param appointment objeto {@link Appointment} a reprogramar
     * @param newDateTime nueva fecha y hora de la cita
     * @throws Exception si la cita no existe o la nueva fecha no es válida
     */
    public void reschedule(Appointment appointment, LocalDateTime newDateTime) throws Exception {
        if (newDateTime == null || newDateTime.isBefore(LocalDateTime.now())) {
            throw new Exception("La nueva fecha debe ser futura");
        }

        Appointment existing = appointmentPort.findById(appointment);
        if (existing == null) {
            throw new Exception("No se encontró la cita para reprogramar");
        }

        existing.setDateTime(newDateTime);
        appointmentPort.save(existing);
    }
}
