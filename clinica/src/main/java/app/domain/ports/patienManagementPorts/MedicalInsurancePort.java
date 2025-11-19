package app.domain.ports.patienManagementPorts;

import app.domain.model.patienManagement.MedicalInsurance;
import java.util.Optional;

public interface MedicalInsurancePort {

    Optional<MedicalInsurance> findByPolicyNumber(String policyNumber);

    Optional<MedicalInsurance> findByInsurer(String insurer);

    MedicalInsurance save(MedicalInsurance insurance);

    void delete(Long id);

    boolean isEmpty();
}
