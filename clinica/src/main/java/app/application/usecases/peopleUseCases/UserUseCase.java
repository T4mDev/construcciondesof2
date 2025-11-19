package app.application.usecases.peopleUseCases;

import app.domain.model.enums.Role;
import app.domain.model.people.User;
import app.domain.services.peopleServices.UserService;

import org.springframework.stereotype.Component;

@Component
public class UserUseCase {

    private final UserService userService;

    public UserUseCase(UserService userService) {
        this.userService = userService;
    }

    public void createUser(User userToCreate, User creator) throws Exception {
        userService.create(userToCreate, creator);
    }

    public void deleteUser(User user) throws Exception {
        userService.delete(user);
    }

    public User findUserByUsername(String username) throws Exception {
        return userService.findByUsername(username);
    }

    public User findUserByDocument(String document) throws Exception {
        return userService.findByDocument(document);
    }

    public void updateUserPassword(User user, String newPassword) throws Exception {
        userService.updatePassword(user, newPassword);
    }

    public void assignUserRole(User user, Role role) throws Exception {
        userService.assignRole(user, role);
    }

    public boolean isEmpty() {
        return userService.isEmpty();
    }
}
