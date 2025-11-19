package app.application.usecases.schedulingUseCases;

import app.domain.model.scheduling.Appointment;
import app.domain.services.schedulingServices.AppointmentService;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppointmentUseCase {
    public Appointment findAppointmentById(Long id) throws Exception {
        Appointment temp = new Appointment();
        temp.setId(id);
        return appointmentService.find(temp);
    }

    private final AppointmentService appointmentService;

    public AppointmentUseCase(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void createAppointment(Appointment appointment) throws Exception {
        appointmentService.create(appointment);
    }

    public void cancelAppointment(Appointment appointment) throws Exception {
        appointmentService.cancel(appointment);
    }

    public Appointment findAppointment(Appointment appointment) throws Exception {
        return appointmentService.find(appointment);
    }

    public void rescheduleAppointment(Appointment appointment, LocalDateTime newDateTime) throws Exception {
        appointmentService.reschedule(appointment, newDateTime);
    }
}
