package app.adapter.in.rest.request;

public class HealthMetricsRequest {

	private String id;
	private String patientDocument;
	private String bloodPressure;
	private String temperature;
	private String pulse;
	private String oxygenLevel;
	private String healthMetricsDetails;

	public HealthMetricsRequest() {
	}

	public HealthMetricsRequest(String id, String patientDocument, String bloodPressure, String temperature,
			String pulse, String oxygenLevel, String healthMetricsDetails) {
		this.id = id;
		this.patientDocument = patientDocument;
		this.bloodPressure = bloodPressure;
		this.temperature = temperature;
		this.pulse = pulse;
		this.oxygenLevel = oxygenLevel;
		this.healthMetricsDetails = healthMetricsDetails;
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

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public String getOxygenLevel() {
		return oxygenLevel;
	}

	public void setOxygenLevel(String oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}

	public String getHealthMetricsDetails() {
		return healthMetricsDetails;
	}

	public void setHealthMetricsDetails(String healthMetricsDetails) {
		this.healthMetricsDetails = healthMetricsDetails;
	}
}
