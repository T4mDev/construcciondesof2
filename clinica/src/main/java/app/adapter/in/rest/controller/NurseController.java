package app.adapter.in.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.in.rest.request.HealthMetricsRequest;
import app.adapter.in.rest.request.ClinicalNoteRequest;
import app.application.usecases.patientManagementUseCases.HealthMetricsUseCase;
import app.application.usecases.medicalRecordUseCases.ClinicalNoteUseCase;
import app.domain.model.patienManagement.HealthMetrics;
import app.domain.model.medicalRecords.ClinicalNote;
import app.domain.model.patienManagement.Patient;
import app.domain.model.people.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/nurse")
public class NurseController {

	@Autowired
	private HealthMetricsUseCase healthMetricsUseCase;

	@Autowired
	private ClinicalNoteUseCase clinicalNoteUseCase;

	@PostMapping("/healthmetrics")
	public ResponseEntity<String> createHealthMetrics(@RequestBody HealthMetricsRequest req,
			@RequestParam(name = "patientDocument") String patientDocument) {
		try {
			HealthMetrics metrics = new HealthMetrics();
			metrics.setPatientDocument(patientDocument);
			metrics.setBloodPressure(req.getBloodPressure());
			try {
				if (req.getTemperature() != null && !req.getTemperature().isEmpty()) {
					metrics.setTemperature(Double.parseDouble(req.getTemperature()));
				}
				if (req.getPulse() != null && !req.getPulse().isEmpty()) {
					metrics.setPulse(Integer.parseInt(req.getPulse()));
				}
				if (req.getOxygenLevel() != null && !req.getOxygenLevel().isEmpty()) {
					metrics.setOxygenLevel(Double.parseDouble(req.getOxygenLevel()));
				}
			} catch (NumberFormatException nfe) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valores numéricos inválidos en métricas");
			}
			metrics.setHealthMetricsDetails(req.getHealthMetricsDetails());
			healthMetricsUseCase.createHealthMetrics(metrics, patientDocument);
			return ResponseEntity.status(HttpStatus.CREATED).body("Métricas creadas");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/healthmetrics/{patientDocument}")
	public ResponseEntity<HealthMetrics> getHealthMetrics(@PathVariable String patientDocument) {
		try {
			HealthMetrics metrics = healthMetricsUseCase.findHealthMetricsByPatient(patientDocument);
			return ResponseEntity.ok(metrics);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/healthmetrics/{patientDocument}")
	public ResponseEntity<String> updateHealthMetrics(@PathVariable String patientDocument,
			@RequestBody HealthMetricsRequest req) {
		try {
			HealthMetrics metrics = new HealthMetrics();
			metrics.setPatientDocument(patientDocument);
			metrics.setBloodPressure(req.getBloodPressure());
			try {
				if (req.getTemperature() != null && !req.getTemperature().isEmpty()) {
					metrics.setTemperature(Double.parseDouble(req.getTemperature()));
				}
				if (req.getPulse() != null && !req.getPulse().isEmpty()) {
					metrics.setPulse(Integer.parseInt(req.getPulse()));
				}
				if (req.getOxygenLevel() != null && !req.getOxygenLevel().isEmpty()) {
					metrics.setOxygenLevel(Double.parseDouble(req.getOxygenLevel()));
				}
			} catch (NumberFormatException nfe) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valores numéricos inválidos en métricas");
			}
			metrics.setHealthMetricsDetails(req.getHealthMetricsDetails());
			healthMetricsUseCase.updateHealthMetrics(metrics, patientDocument);
			return ResponseEntity.ok("Métricas actualizadas");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/healthmetrics/{patientDocument}")
	public ResponseEntity<String> deleteHealthMetrics(@PathVariable String patientDocument) {
		try {
			healthMetricsUseCase.deleteHealthMetrics(patientDocument);
			return ResponseEntity.ok("Métricas eliminadas");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/clinical-notes")
	public ResponseEntity<String> createClinicalNote(@RequestBody ClinicalNoteRequest req) {
		try {
			ClinicalNote note = new ClinicalNote();
			// set patient and doctor minimal info
			if (req.getPatientId() != null && !req.getPatientId().isEmpty()) {
				try {
					Patient p = new Patient();
					p.setId(Long.parseLong(req.getPatientId()));
					note.setPatient(p);
				} catch (NumberFormatException nfe) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient id inválido");
				}
			}
			if (req.getDoctorId() != null && !req.getDoctorId().isEmpty()) {
				try {
					User d = new User();
					d.setId(Long.parseLong(req.getDoctorId()));
					note.setDoctor(d);
				} catch (NumberFormatException nfe) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Doctor id inválido");
				}
			}
			note.setNote(req.getNote());
			if (req.getDateTime() != null && !req.getDateTime().isEmpty()) {
				try {
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					LocalDateTime dt = LocalDateTime.parse(req.getDateTime(), fmt);
					note.setConsultationDateTime(dt);
				} catch (Exception ex) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Formato de fecha inválido, use yyyy-MM-dd HH:mm");
				}
			}
			clinicalNoteUseCase.createClinicalNote(note);
			return ResponseEntity.status(HttpStatus.CREATED).body("Nota clínica creada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/clinical-notes/{patientId}")
	public ResponseEntity<ClinicalNote> getClinicalNoteByPatient(@PathVariable long patientId) {
		try {
			ClinicalNote note = clinicalNoteUseCase.findClinicalNoteByPatient(patientId);
			return ResponseEntity.ok(note);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/clinical-notes/{patientId}")
	public ResponseEntity<String> deleteClinicalNote(@PathVariable long patientId) {
		try {
			ClinicalNote note = new ClinicalNote();
			var p = new app.domain.model.patienManagement.Patient();
			p.setId(patientId);
			note.setPatient(p);
			clinicalNoteUseCase.deleteClinicalNote(note);
			return ResponseEntity.ok("Nota clínica eliminada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
