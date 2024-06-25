package physiodesk.physiodesk_backend.Users.interfaces.rest.transform.physio;

import physiodesk.physiodesk_backend.Users.domain.model.aggregates.physio;
import physiodesk.physiodesk_backend.Users.interfaces.rest.resources.physio.physioResource;

public class physioResourceFromEntityAssembler {
    public static physioResource fromEntity(physio entity) {
        return new physioResource(entity.getId(), entity.getNombre(), entity.getImagen(), entity.getBiografia(), entity.getEdad(), entity.getHorario());
    }
}