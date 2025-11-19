package app.adapter.in.rest.request;

public class InvoiceRequest {

	private String id;
	private String patientId;
	private String policyNumber;
	private String amount;
	private String description;
	private String dateTime; // formato yyyy-MM-dd HH:mm
	private String invoiceCode;

	public InvoiceRequest() {
	}

	public InvoiceRequest(String id, String patientId, String policyNumber, String amount, String description,
			String dateTime, String invoiceCode) {
		this.id = id;
		this.patientId = patientId;
		this.policyNumber = policyNumber;
		this.amount = amount;
		this.description = description;
		this.dateTime = dateTime;
		this.invoiceCode = invoiceCode;
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

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
}
