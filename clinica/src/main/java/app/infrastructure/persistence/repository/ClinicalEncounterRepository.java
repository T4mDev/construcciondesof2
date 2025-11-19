package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.ClinicalEncounterEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicalEncounterRepository extends JpaRepository<ClinicalEncounterEntity, Long> {
	Optional<ClinicalEncounterEntity> findByPatientDocument(String patientDocument);
}
