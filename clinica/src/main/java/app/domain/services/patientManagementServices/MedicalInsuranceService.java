package app.domain.services.patientManagementServices;

import app.domain.model.patienManagement.MedicalInsurance;
import app.domain.ports.patienManagementPorts.MedicalInsurancePort;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio para la gestión de pólizas médicas.
 * Encapsula las reglas de negocio relacionadas con la creación, validación,
 * verificación de vigencia y búsqueda de pólizas médicas, delegando la
 * persistencia
 * en {@link MedicalInsurancePort}.
 */
@Service
public class MedicalInsuranceService {

    @Autowired
    private final MedicalInsurancePort medicalInsurancePort;

    /**
     * Constructor que inyecta el puerto de persistencia de pólizas médicas.
     * 
     * @param medicalInsurancePort instancia de {@link MedicalInsurancePort}
     */
    public MedicalInsuranceService(MedicalInsurancePort medicalInsurancePort) {
        this.medicalInsurancePort = medicalInsurancePort;
    }

    /**
     * Crea una nueva póliza médica en el sistema.
     * Reglas de negocio:
     * <ul>
     * <li>El número de póliza es obligatorio.</li>
     * <li>El nombre de la compañía es obligatorio.</li>
     * <li>La fecha de vigencia debe ser futura.</li>
     * <li>La póliza debe estar activa al momento de registrarse.</li>
     * <li>No puede existir otra póliza con el mismo número.</li>
     * </ul>
     * 
     * @param policy objeto {@link MedicalInsurance} a crear
     * @throws Exception si alguna regla de negocio es violada
     */
    public void create(MedicalInsurance policy) throws Exception {
        if (policy.getPolicyNumber() == null || policy.getPolicyNumber().isEmpty()) {
            throw new Exception("El número de póliza es obligatorio");
        }

        if (policy.getCompanyName() == null || policy.getCompanyName().isEmpty()) {
            throw new Exception("El nombre de la compañía es obligatorio");
        }

        if (policy.getEndDate() == null || policy.getEndDate().isBefore(LocalDate.now())) {
            throw new Exception("La fecha de vigencia debe ser futura");
        }

        if (!policy.isActive()) {
            throw new Exception("La póliza debe estar activa al momento de registrarse");
        }

        Optional<MedicalInsurance> existing = medicalInsurancePort.findByPolicyNumber(policy.getPolicyNumber());
        if (existing.isPresent()) {
            throw new Exception("Ya existe una póliza con ese número");
        }

        medicalInsurancePort.save(policy);
    }

    /**
     * Valida una póliza médica por su número.
     * Reglas de negocio:
     * <ul>
     * <li>La póliza debe existir.</li>
     * <li>Debe estar activa.</li>
     * <li>No debe estar expirada.</li>
     * </ul>
     * 
     * @param policyNumber número de póliza
     * @return true si la póliza es válida, false en caso contrario
     * @throws Exception si la póliza no existe
     */
    public boolean validatePolicy(String policyNumber) throws Exception {
        Optional<MedicalInsurance> policyOpt = medicalInsurancePort.findByPolicyNumber(policyNumber);
        if (policyOpt.isEmpty()) {
            throw new Exception("No se encontró póliza con ese número");
        }
        MedicalInsurance policy = policyOpt.get();
        return policy.isActive() && !checkExpiration(policy);
    }

    /**
     * Verifica si una póliza está expirada.
     * Regla de negocio: la póliza se considera expirada si la fecha de fin es
     * anterior a la fecha actual.
     * 
     * @param policy objeto {@link MedicalInsurance} a verificar
     * @return true si la póliza está expirada, false en caso contrario
     */
    public boolean checkExpiration(MedicalInsurance policy) {
        return policy.getEndDate() != null && policy.getEndDate().isBefore(LocalDate.now());
    }

    /**
     * Busca una póliza médica por su número.
     * Regla de negocio: la póliza debe existir en el sistema.
     * 
     * @param policyNumber número de póliza
     * @return póliza encontrada
     * @throws Exception si no se encuentra la póliza
     */
    public MedicalInsurance findByPatient(String policyNumber) throws Exception {
        Optional<MedicalInsurance> policyOpt = medicalInsurancePort.findByPolicyNumber(policyNumber);
        if (policyOpt.isEmpty()) {
            throw new Exception("No se encontró póliza con ese número");
        }
        return policyOpt.get();
    }
}
