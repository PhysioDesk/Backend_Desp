package physiodesk.physiodesk_backend.Users.interfaces.rest.transform.user;

import physiodesk.physiodesk_backend.Users.domain.model.commands.user.CreateUserCommand;
import physiodesk.physiodesk_backend.Users.interfaces.rest.resources.user.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand fromResource(CreateUserResource resource) {
        return new CreateUserCommand(resource.name(), resource.lastname(), resource.phone(), resource.email(), resource.address(), resource.city(), resource.password());
    }
}
