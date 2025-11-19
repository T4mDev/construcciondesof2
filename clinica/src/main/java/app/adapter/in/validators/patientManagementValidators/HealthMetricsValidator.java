package app.adapter.in.validators.patientManagementValidators;

import org.springframework.stereotype.Component;

import app.adapter.in.validators.SimpleValidator;

@Component
public class HealthMetricsValidator extends SimpleValidator {

    public double validateBloodPressure(String value) throws Exception {
        double pressure = doubleValidator("presión arterial", value);
        if (pressure <= 0 || pressure > 300) {
            throw new Exception("La presión arterial debe estar en un rango clínico válido (0–300 mmHg)");
        }
        return pressure;
    }

    public double validateTemperature(String value) throws Exception {
        double temp = doubleValidator("temperatura corporal", value);
        if (temp < 30 || temp > 45) {
            throw new Exception("La temperatura debe estar entre 30°C y 45°C");
        }
        return temp;
    }

    public int validatePulse(String value) throws Exception {
        int pulse = integerValidator("pulso", value);
        if (pulse <= 0 || pulse > 220) {
            throw new Exception("El pulso debe estar en un rango clínico válido (1–220 bpm)");
        }
        return pulse;
    }

    public double validateOxygenLevel(String value) throws Exception {
        double oxygen = doubleValidator("nivel de oxígeno", value);
        if (oxygen < 50 || oxygen > 100) {
            throw new Exception("El nivel de oxígeno debe estar entre 50% y 100%");
        }
        return oxygen;
    }

    public String validatePatientDocument(String value) throws Exception {
        return stringValidator("documento del paciente", value);
    }
}
