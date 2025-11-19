package app.adapter.in.rest.request;

public class MedicalInsuranceRequest {

	private String id;
	private String companyName;
	private String policyNumber;
	private String active; // "true" or "false"
	private String endDate; // formato yyyy-MM-dd
	private String insurer;

	public MedicalInsuranceRequest() {
	}

	public MedicalInsuranceRequest(String id, String companyName, String policyNumber, String active, String endDate,
			String insurer) {
		this.id = id;
		this.companyName = companyName;
		this.policyNumber = policyNumber;
		this.active = active;
		this.endDate = endDate;
		this.insurer = insurer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getInsurer() {
		return insurer;
	}

	public void setInsurer(String insurer) {
		this.insurer = insurer;
	}
}
