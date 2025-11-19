package app.adapter.in.rest.request;

import java.time.LocalDateTime;

public class AppointmentRequest {
	private Long id;
	private String patientDocument;
	private String doctorDocument;
	private LocalDateTime dateTime;
	private String reason;

	public AppointmentRequest() {
	}

	public AppointmentRequest(Long id, String patientDocument, String doctorDocument, LocalDateTime dateTime, String reason) {
		this.id = id;
		this.patientDocument = patientDocument;
		this.doctorDocument = doctorDocument;
		this.dateTime = dateTime;
		this.reason = reason;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatientDocument() {
		return patientDocument;
	}

	public void setPatientDocument(String patientDocument) {
		this.patientDocument = patientDocument;
	}

	public String getDoctorDocument() {
		return doctorDocument;
	}

	public void setDoctorDocument(String doctorDocument) {
		this.doctorDocument = doctorDocument;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
