package app.adapter.in.rest.request;

public class MedicalRecordRequest {

	private String id;
	private String patientDocument;
	private String patientName;
	private String recordDetails;
	private String entries; // JSON or serialized representation of entries

	public MedicalRecordRequest() {
	}

	public MedicalRecordRequest(String id, String patientDocument, String patientName, String recordDetails,
			String entries) {
		this.id = id;
		this.patientDocument = patientDocument;
		this.patientName = patientName;
		this.recordDetails = recordDetails;
		this.entries = entries;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientDocument() {
		return patientDocument;
	}

	public void setPatientDocument(String patientDocument) {
		this.patientDocument = patientDocument;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getRecordDetails() {
		return recordDetails;
	}

	public void setRecordDetails(String recordDetails) {
		this.recordDetails = recordDetails;
	}

	public String getEntries() {
		return entries;
	}

	public void setEntries(String entries) {
		this.entries = entries;
	}
}
