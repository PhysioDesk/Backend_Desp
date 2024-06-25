package physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries;

public record GetAllAppointmentsByPhysiotherapistIdQuery(Long physiotherapistId) {

    public GetAllAppointmentsByPhysiotherapistIdQuery {
        if (physiotherapistId == null) {
            throw new IllegalArgumentException("Physiotherapist id must be greater than 0");
        }
    }
}
