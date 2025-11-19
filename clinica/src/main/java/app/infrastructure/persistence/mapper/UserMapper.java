package app.infrastructure.persistence.mapper;

import app.domain.model.enums.Role;
import app.domain.model.people.User;
import app.infrastructure.persistence.entities.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        if (user.getRole() != null) {
            entity.setRole(user.getRole().name());
        }
        entity.setBirthDate(user.getBirthDate());

        return entity;
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setEmail(entity.getEmail());
        if (entity.getRole() != null) {
            user.setRole(Role.valueOf(entity.getRole()));
        }
        user.setBirthDate(entity.getBirthDate());

        return user;
    }
}
