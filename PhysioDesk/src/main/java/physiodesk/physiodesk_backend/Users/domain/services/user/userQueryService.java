package physiodesk.physiodesk_backend.Users.domain.services.user;

import physiodesk.physiodesk_backend.Users.domain.model.aggregates.user;
import physiodesk.physiodesk_backend.Users.domain.model.queries.user.GetAllUsersQuery;
import physiodesk.physiodesk_backend.Users.domain.model.queries.user.GetUserById;

import java.util.List;
import java.util.Optional;

public interface userQueryService {

    List<user> handle(GetAllUsersQuery query);
    Optional<user> handle(GetUserById query);
    Optional<user> getUserByEmail(String email);
    Optional<user> getUserByPassword(String password);
    Optional<user> getUserByEmailAndPassword(String email, String password);
}
