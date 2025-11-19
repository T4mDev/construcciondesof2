package app.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import app.infrastructure.persistence.entities.EmergencyContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContactEntity, Long> {

	Optional<EmergencyContactEntity> findByPatientDocument(String patientDocument);

}
