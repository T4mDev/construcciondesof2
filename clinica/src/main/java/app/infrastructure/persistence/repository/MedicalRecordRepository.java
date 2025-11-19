package app.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import app.infrastructure.persistence.entities.MedicalRecordEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {
    Optional<MedicalRecordEntity> findByPatientDocument(String patientDocument);
}
