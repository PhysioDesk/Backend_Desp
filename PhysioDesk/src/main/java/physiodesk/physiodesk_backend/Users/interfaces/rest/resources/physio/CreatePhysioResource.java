package physiodesk.physiodesk_backend.Users.interfaces.rest.resources.physio;

import physiodesk.physiodesk_backend.Users.domain.model.entities.horarios;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record CreatePhysioResource(long id, String nombre, String imagen, String biografia, Short edad, List<horarios> horario) {
    public CreatePhysioResource{
        if (id < 0){
            throw new IllegalArgumentException("Id must be greater than or equal to 0");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre cannot be null or empty");
        }
        if (imagen == null || imagen.isBlank()) {
            throw new IllegalArgumentException("Imagen cannot be null or empty");
        }
        if (biografia == null || biografia.isBlank()) {
            throw new IllegalArgumentException("Biografia cannot be null or empty");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("Edad must be greater than 0");
        }
        if (horario == null || horario.isEmpty()) {
            throw new IllegalArgumentException("Horarios cannot be null or empty");
        }
    }
}
