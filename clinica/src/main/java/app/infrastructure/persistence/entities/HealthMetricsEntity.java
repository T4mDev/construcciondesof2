package app.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "health_metrics")
public class HealthMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientDocument;

    @Column(nullable = false)
    private String bloodPressure;

    @Column(nullable = false)
    private double temperature;

    @Column(nullable = false)
    private int pulse;

    @Column(nullable = false)
    private double oxygenLevel;

    @Column(nullable = false)
    private String healthMetricsEntityDetails;
    // Add other fields based on HealthMetricsEntitys model

    // Getters and Setters
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

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public double getOxygenLevel() {
        return oxygenLevel;
    }

    public void setOxygenLevel(double oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }

    public String getHealthMetricsEntityDetails() {
        return healthMetricsEntityDetails;
    }

    public void setHealthMetricsEntityDetails(String healthMetricsEntityDetails) {
        this.healthMetricsEntityDetails = healthMetricsEntityDetails;
    }
}