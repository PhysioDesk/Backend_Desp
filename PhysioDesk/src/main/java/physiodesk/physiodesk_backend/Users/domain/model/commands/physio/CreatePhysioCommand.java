package physiodesk.physiodesk_backend.Users.domain.model.commands.physio;

import physiodesk.physiodesk_backend.Users.domain.model.entities.horarios;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record CreatePhysioCommand(long id, String nombre, String imagen, String biografia, Short edad, List<horarios> horario) {

    public CreatePhysioCommand{
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre cannot be null");
        }
        if (imagen == null || imagen.isBlank()) {
            throw new IllegalArgumentException("Imagen cannot be null");
        }
        if (biografia == null || biografia.isBlank()) {
            throw new IllegalArgumentException("Biografia cannot be null");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("Edad must be greater than 0");
        }
        if (horario == null || horario.isEmpty()) {
            throw new IllegalArgumentException("Horarios cannot be null");
        }
    }
}
