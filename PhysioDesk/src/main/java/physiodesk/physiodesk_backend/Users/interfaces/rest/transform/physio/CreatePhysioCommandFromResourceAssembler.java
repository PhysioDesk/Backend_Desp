package physiodesk.physiodesk_backend.Users.interfaces.rest.transform.physio;

import physiodesk.physiodesk_backend.Users.domain.model.commands.physio.CreatePhysioCommand;
import physiodesk.physiodesk_backend.Users.interfaces.rest.resources.physio.CreatePhysioResource;

public class CreatePhysioCommandFromResourceAssembler {
    public static CreatePhysioCommand fromResource(CreatePhysioResource resource) {
        return new CreatePhysioCommand(resource.id(), resource.nombre(), resource.imagen(), resource.biografia(), resource.edad(), resource.horario());
    }
}
