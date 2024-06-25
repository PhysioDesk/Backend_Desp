package physiodesk.physiodesk_backend.Users.interfaces.rest.transform.user;

import physiodesk.physiodesk_backend.Users.domain.model.aggregates.user;
import physiodesk.physiodesk_backend.Users.interfaces.rest.resources.user.userResource;

public class userResourceFromEntityAssembler {
    public static userResource fromEntity(user entity) {
        return new userResource(entity.getId(), entity.getName(), entity.getLastname(), entity.getPhone(), entity.getEmail(), entity.getAddress(), entity.getCity(), entity.getPassword());
    }
}
