package app.infrastructure.persistence.repository;

import app.domain.model.people.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDocument(String document);

    Optional<User> findByUsername(String username);
}
