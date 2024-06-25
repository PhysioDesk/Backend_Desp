package physiodesk.physiodesk_backend.Users.domain.services.physio;

import physiodesk.physiodesk_backend.Users.domain.model.aggregates.physio;
import physiodesk.physiodesk_backend.Users.domain.model.commands.physio.CreatePhysioCommand;


import java.util.Optional;

public interface physioCommandService {
    Optional<physio> handle(CreatePhysioCommand command);
    boolean deletePhysioById(Long id);
    Optional<physio> updatePhysioById(Long id, physio command);
}
