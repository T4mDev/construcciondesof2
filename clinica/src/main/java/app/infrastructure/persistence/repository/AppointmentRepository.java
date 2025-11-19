package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.infrastructure.persistence.entities.AppointmentEntity;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByPatientId(Long patientId);

    List<AppointmentEntity> findByDoctorId(Long doctorId);

    List<AppointmentEntity> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
