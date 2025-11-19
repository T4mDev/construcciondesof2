package app.adapter.out.persistence.peopleAdapters;

import app.domain.model.people.User;
import app.domain.ports.peoplePorts.UserPort;
import app.infrastructure.persistence.repository.UserRepository;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements UserPort {

    private final UserRepository repository;

    public UserAdapter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findByDocument(String document) {
        return repository.findByDocument(document);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }
}
