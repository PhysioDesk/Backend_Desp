package physiodesk.physiodesk_backend.Users.domain.model.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import physiodesk.physiodesk_backend.Users.domain.model.aggregates.physio;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Embeddable
public class horarios {


    @Column(nullable = false)
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;

    @Column(nullable = false)
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private String hora;

    public horarios() {
    }

    public horarios(Date fecha, String hora) {
        this.fecha = fecha;
        this.hora = hora;
    }
}
