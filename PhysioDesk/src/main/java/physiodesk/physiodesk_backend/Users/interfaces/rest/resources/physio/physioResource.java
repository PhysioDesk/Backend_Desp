package physiodesk.physiodesk_backend.Users.interfaces.rest.resources.physio;

import physiodesk.physiodesk_backend.Users.domain.model.entities.horarios;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record physioResource(Long id, String nombre, String imagen, String biografia, Short edad, List<horarios> horario) {
}
