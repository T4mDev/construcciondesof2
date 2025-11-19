package app.domain.ports.peoplePorts;

import java.util.Optional;

import app.domain.model.people.User;

public interface UserPort {

    Optional<User> findByDocument(String document); // ← AHORA SÍ

    Optional<User> findByUsername(String username);

    User save(User user);

    void delete(long id);

    boolean isEmpty();
}
