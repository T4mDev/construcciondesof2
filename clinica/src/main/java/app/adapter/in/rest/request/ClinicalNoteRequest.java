package app.adapter.in.rest.request;

public class ClinicalNoteRequest {

	private String id;
	private String patientId;
	private String doctorId;
	private String note;
	private String dateTime; // formato yyyy-MM-dd HH:mm

	public ClinicalNoteRequest() {
	}

	public ClinicalNoteRequest(String id, String patientId, String doctorId, String note, String dateTime) {
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.note = note;
		this.dateTime = dateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
