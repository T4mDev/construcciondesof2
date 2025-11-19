package app.adapter.in.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.in.rest.request.AppointmentRequest;
import app.adapter.in.rest.request.MedicalOrderRequest;
import app.adapter.in.rest.request.ClinicalEncounterRequest;
import app.application.usecases.schedulingUseCases.AppointmentUseCase;
import app.application.usecases.medicalOrderUseCases.MedicalOrderUseCase;
import app.application.usecases.medicalRecordUseCases.ClinicalEncounterUseCase;
import app.domain.model.scheduling.Appointment;
import app.domain.model.medicalOrders.MedicalOrder;
import app.domain.model.medicalRecords.ClinicalEncounter;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/doctors")
public class DoctorsController {

    @Autowired
    private AppointmentUseCase appointmentUseCase;

    @Autowired
    private MedicalOrderUseCase medicalOrderUseCase;

    @Autowired
    private ClinicalEncounterUseCase clinicalEncounterUseCase;

    @PostMapping("/appointments")
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentRequest req) {
        try {
            Appointment appointment = new Appointment();
            appointment.setPatientDocument(req.getPatientDocument());
            appointment.setDoctorDocument(req.getDoctorDocument());
            appointment.setDateTime(req.getDateTime());
            appointment.setReason(req.getReason());

            appointmentUseCase.createAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cita creada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
        try {
            Appointment temp = new Appointment();
            temp.setId(id);
            appointmentUseCase.cancelAppointment(temp);
            return ResponseEntity.ok("Cita cancelada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/appointments/{id}/reschedule")
    public ResponseEntity<String> rescheduleAppointment(@PathVariable Long id,
            @RequestParam(name = "newDateTime") String newDateTimeStr) {
        try {
            Appointment temp = new Appointment();
            temp.setId(id);
            LocalDateTime newDate = LocalDateTime.parse(newDateTimeStr);
            appointmentUseCase.rescheduleAppointment(temp, newDate);
            return ResponseEntity.ok("Cita reprogramada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<String> createMedicalOrder(@RequestBody MedicalOrderRequest req) {
        try {
            MedicalOrder order = new MedicalOrder();
            order.setOrderNumber(req.getOrderNumber());
            order.setItems(req.getItems());
            order.setOrderDetails(req.getOrderDetails());

            medicalOrderUseCase.createMedicalOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body("Orden médica creada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/encounters")
    public ResponseEntity<String> createClinicalEncounter(@RequestBody ClinicalEncounterRequest req,
            @RequestParam(name = "patientDocument") String patientDocument) {
        try {
            ClinicalEncounter encounter = new ClinicalEncounter();
            encounter.setClinicalNote(req.getClinicalNote());
            encounter.setMedicalOrder(req.getMedicalOrder());
            encounter.setHealthMetrics(req.getHealthMetrics());
            encounter.setEncounterDetails(req.getEncounterDetails());

            clinicalEncounterUseCase.createClinicalRecord(encounter, patientDocument);
            return ResponseEntity.status(HttpStatus.CREATED).body("Encuentro clínico creado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
