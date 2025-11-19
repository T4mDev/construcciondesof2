package app.adapter.out.persistence.patientManagementAdapters;

import app.domain.model.patienManagement.MedicalInsurance;
import app.domain.ports.patienManagementPorts.MedicalInsurancePort;
import app.infrastructure.persistence.repository.MedicalInsuranceRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MedicalInsuranceAdapter implements MedicalInsurancePort {

    private final MedicalInsuranceRepository repository;

    public MedicalInsuranceAdapter(MedicalInsuranceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<MedicalInsurance> findByPolicyNumber(String policyNumber) {
        return repository.findByPolicyNumber(policyNumber);
    }

    @Override
    public Optional<MedicalInsurance> findByInsurer(String insurer) {
        return repository.findByInsurer(insurer);
    }

    @Override
    public MedicalInsurance save(MedicalInsurance insurance) {
        return repository.save(insurance);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }
}
