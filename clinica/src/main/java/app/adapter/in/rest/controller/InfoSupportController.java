package app.adapter.in.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.application.usecases.patientManagementUseCases.PatientUseCase;
import app.application.usecases.schedulingUseCases.AppointmentUseCase;
import app.application.usecases.inventoryUseCases.StockItemUseCase;
import app.application.usecases.medicalRecordUseCases.ClinicalEncounterUseCase;

import app.domain.model.patienManagement.Patient;
import app.domain.model.scheduling.Appointment;
import app.domain.model.inventory.StockItem;
import app.domain.model.medicalRecords.ClinicalEncounter;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoSupportController {

	@Autowired
	private PatientUseCase patientUseCase;

	@Autowired
	private AppointmentUseCase appointmentUseCase;

	@Autowired
	private StockItemUseCase stockItemUseCase;

	@Autowired
	private ClinicalEncounterUseCase clinicalEncounterUseCase;

	@GetMapping("/patients/{document}")
	public ResponseEntity<Patient> getPatientByDocument(@PathVariable String document) {
		try {
			Patient p = patientUseCase.findPatientByDocument(document);
			return p != null ? ResponseEntity.ok(p) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			// Loguea el error para trazabilidad
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
		try {
			Appointment found = appointmentUseCase.findAppointmentById(id);
			return found != null ? ResponseEntity.ok(found) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/stock")
	public ResponseEntity<List<StockItem>> findStockByType(@RequestParam(name = "type", required = false) String type) {
		try {
			List<StockItem> list = (type != null) ? stockItemUseCase.findItemsByCategory(type)
					: stockItemUseCase.findAllItems();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/encounters/{patientDocument}")
	public ResponseEntity<ClinicalEncounter> getClinicalEncounter(@PathVariable String patientDocument) {
		try {
			ClinicalEncounter record = clinicalEncounterUseCase.findClinicalRecord(patientDocument);
			return record != null ? ResponseEntity.ok(record) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
