package app.domain.ports.schedulingPorts;

import app.domain.model.scheduling.Appointment;
import java.util.List;
import java.time.LocalDateTime;

public interface AppointmentPort {
    Appointment findById(Appointment appointment) throws Exception;

    List<Appointment> findByDoctorDocumentAndDateTimeBetween(String doctorDocument, LocalDateTime startDate,
            LocalDateTime endDate) throws Exception;

    void save(Appointment appointment) throws Exception;

    void cancel(Appointment appointment) throws Exception;
}