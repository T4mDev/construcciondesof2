package app.adapter.in.rest.request;

public class PatientRequest {

	private String id;
	private String fullName;
	private String gender;
	private String birthDate;
	private String phone;
	private String address;
	private String document;

	public PatientRequest() {
	}

	public PatientRequest(String id, String fullName, String gender, String birthDate, String phone, String address,
			String document) {
		this.id = id;
		this.fullName = fullName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.phone = phone;
		this.address = address;
		this.document = document;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
}
