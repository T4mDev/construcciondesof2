package app.adapter.in.rest.request;

public class EmergencyContactRequest {

	private Long id;
	private String patientDocument;
	private String firstName;
	private String lastName;
	private String relationship;
	private String phone;

	public EmergencyContactRequest() {
	}

	public EmergencyContactRequest(Long id, String patientDocument, String firstName, String lastName,
			String relationship, String phone) {
		this.id = id;
		this.patientDocument = patientDocument;
		this.firstName = firstName;
		this.lastName = lastName;
		this.relationship = relationship;
		this.phone = phone;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
