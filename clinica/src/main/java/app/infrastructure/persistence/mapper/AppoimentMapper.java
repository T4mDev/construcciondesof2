package app.infrastructure.persistence.mapper;

import app.domain.model.scheduling.Appointment;
import app.infrastructure.persistence.entities.AppointmentEntity;

public class AppoimentMapper {

    public static AppointmentEntity toEntity(Appointment appointment) {
        if (appointment == null)
            return null;
        AppointmentEntity entity = new AppointmentEntity();
        entity.setId(appointment.getId());
        entity.setReason(appointment.getReason());
        entity.setDateTime(appointment.getDateTime());
        return entity;
    }

    public static Appointment toDomain(AppointmentEntity entity) {
        if (entity == null)
            return null;
        Appointment appointment = new Appointment();
        appointment.setId(entity.getId());
        appointment.setReason(entity.getReason());
        appointment.setDateTime(entity.getDateTime());
        return appointment;
    }
}
