package physiodesk.physiodesk_backend.Users.domain.services.user;

import physiodesk.physiodesk_backend.Users.domain.model.aggregates.user;
import physiodesk.physiodesk_backend.Users.domain.model.commands.user.CreateUserCommand;

import java.util.Optional;

public interface userCommandService {
    Optional<user> handle(CreateUserCommand command);
    boolean deleteUserById(Long id);
    Optional<user> updateUserById(Long id, user command);
}
