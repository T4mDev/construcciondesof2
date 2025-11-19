package app.infrastructure.persistence.repository;

import app.domain.model.patienManagement.MedicalInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalInsuranceRepository extends JpaRepository<MedicalInsurance, Long> {

    Optional<MedicalInsurance> findByPolicyNumber(String policyNumber);

    Optional<MedicalInsurance> findByInsurer(String insurer);
}
