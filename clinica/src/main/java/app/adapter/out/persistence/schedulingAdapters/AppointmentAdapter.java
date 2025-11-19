package app.adapter.out.persistence.schedulingAdapters;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import app.domain.model.scheduling.Appointment;
import app.domain.ports.schedulingPorts.AppointmentPort;
import app.infrastructure.persistence.entities.AppointmentEntity;
import app.infrastructure.persistence.repository.AppointmentRepository;
import app.infrastructure.persistence.mapper.AppoimentMapper;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

@Component
public class AppointmentAdapter implements AppointmentPort {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment findById(Appointment appointment) throws Exception {
        if (appointment == null) throw new Exception("Appointment parameter is null");
        long id = appointment.getId();
        if (id <= 0) throw new Exception("Invalid appointment id");

        Optional<AppointmentEntity> entity = appointmentRepository.findById(id);
        return entity.map(AppoimentMapper::toDomain)
                .orElseThrow(() -> new Exception("Appointment not found with id: " + id));
    }

    @Override
    public void save(Appointment appointment) throws Exception {
        if (appointment == null) throw new Exception("Appointment is null");
        AppointmentEntity entity = AppoimentMapper.toEntity(appointment);
        appointmentRepository.save(entity);
    }

    @Override
    public List<Appointment> findByDoctorDocumentAndDateTimeBetween(String doctorDocument, LocalDateTime startDate,
            LocalDateTime endDate) throws Exception {
        if (doctorDocument == null || startDate == null || endDate == null) {
            throw new Exception("Parámetros inválidos para búsqueda de citas");
        }

        return appointmentRepository.findByDateTimeBetween(startDate, endDate)
                .stream()
                .map(AppoimentMapper::toDomain)
                .filter(a -> a != null && a.getDoctor() != null && doctorDocument.equals(a.getDoctor().getDocument()))
                .toList();
    }

    @Override
    public void cancel(Appointment appointment) throws Exception {
        if (appointment == null) throw new Exception("Appointment is null");

        long id = appointment.getId();
        if (id > 0) {
            appointmentRepository.deleteById(id);
            return;
        }

        // fallback: delete by matching fields (dateTime + patient + doctor) - best-effort
        AppointmentEntity probe = AppoimentMapper.toEntity(appointment);
        if (probe != null) {
            // try to delete by entity equality if repository supports it
            appointmentRepository.findAll()
                    .stream()
                    .filter(e -> e.getDateTime().equals(probe.getDateTime())
                            && ((e.getPatient() == null && probe.getPatient() == null) || (e.getPatient() != null && probe.getPatient() != null && e.getPatient().getId() == probe.getPatient().getId()))
                            && ((e.getDoctor() == null && probe.getDoctor() == null) || (e.getDoctor() != null && probe.getDoctor() != null && e.getDoctor().getId() == probe.getDoctor().getId())))
                    .findFirst()
                    .ifPresent(appointmentRepository::delete);
            return;
        }

        throw new Exception("Appointment to cancel not found");
    }
}
