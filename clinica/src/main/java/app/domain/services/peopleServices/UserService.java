package app.domain.services.peopleServices;

import app.domain.model.enums.Role;
import app.domain.model.people.User;
import app.domain.ports.peoplePorts.UserPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserService {

    @Autowired
    private final UserPort userPort;

    public UserService(UserPort userPort) {
        this.userPort = userPort;
    }

    // ================================
    // CREATE
    // ================================
    public void create(User userToCreate, User creator) throws Exception {

        if (userPort.isEmpty()) {
            validateAccountData(userToCreate);
            userPort.save(userToCreate);
            return;
        }

        if (creator.getRole() != Role.ADMINISTRATIVE_STAFF) {
            throw new Exception("Solo un administrador puede crear cuentas");
        }

        validateAccountData(userToCreate);

        // check username
        if (userPort.findByUsername(userToCreate.getUsername()).isPresent()) {
            throw new Exception("Ya existe una cuenta con ese nombre de usuario");
        }

        // check document
        if (userPort.findByDocument(userToCreate.getDocument()).isPresent()) {
            throw new Exception("Ya existe una cuenta con ese documento");
        }

        userPort.save(userToCreate);
    }

    // ================================
    // DELETE
    // ================================
    public void delete(User account) throws Exception {
        var found = userPort.findByDocument(account.getDocument());

        if (found.isEmpty()) {
            throw new Exception("No se encontró la cuenta para eliminar");
        }

        userPort.delete(found.get().getId());
    }

    // ================================
    // FIND BY USERNAME
    // ================================
    public User findByUsername(String username) throws Exception {

        return userPort.findByUsername(username)
                .orElseThrow(() -> new Exception("No se encontró cuenta con ese nombre de usuario"));
    }

    // ================================
    // FIND BY DOCUMENT
    // ================================
    public User findByDocument(String document) throws Exception {

        return userPort.findByDocument(document)
                .orElseThrow(() -> new Exception("No se encontró cuenta con ese documento"));
    }

    // ================================
    // UPDATE PASSWORD
    // ================================
    public void updatePassword(User account, String newPassword) throws Exception {
        var found = userPort.findByDocument(account.getDocument())
                .orElseThrow(() -> new Exception("No se encontró cuenta para actualizar contraseña"));

        if (!isValidPassword(newPassword)) {
            throw new Exception("La nueva contraseña no cumple con los requisitos");
        }

        found.setPassword(newPassword);
        userPort.save(found);
    }

    // ================================
    // ASSIGN ROLE
    // ================================
    public void assignRole(User account, Role role) throws Exception {
        var found = userPort.findByDocument(account.getDocument())
                .orElseThrow(() -> new Exception("No se encontró cuenta para asignar rol"));

        found.setRole(role);
        userPort.save(found);
    }

    // ================================
    // VALIDATION
    // ================================
    private void validateAccountData(User account) throws Exception {
        if (account.getDocument() == null || account.getDocument().isEmpty()) {
            throw new Exception("El documento es obligatorio");
        }

        if (account.getUsername() == null || !account.getUsername().matches("^[a-zA-Z0-9]{1,15}$")) {
            throw new Exception("El nombre de usuario debe tener máximo 15 caracteres, solo letras y números");
        }

        if (!isValidPassword(account.getPassword())) {
            throw new Exception(
                    "La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial");
        }

        if (account.getEmail() == null || !account.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new Exception("El correo electrónico no es válido");
        }

        if (account.getBirthDate() == null || calculateAge(account.getBirthDate()) > 150) {
            throw new Exception("La fecha de nacimiento no puede superar los 150 años");
        }

        if (account.getRole() == null) {
            throw new Exception("El rol de la cuenta es obligatorio");
        }

        if (account.getDocument() == null || account.getDocument().isEmpty()) {
            throw new Exception("El documento es obligatorio");
        }
    }

    private boolean isValidPassword(String password) {
        return password != null &&
                password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public boolean isEmpty() {
        return userPort.isEmpty();
    }
}
