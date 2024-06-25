package physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries;

public record GetAllAppointmentsByPatientIdQuery(Long patientId) {
    public GetAllAppointmentsByPatientIdQuery {
        if (patientId == null) {
            throw new IllegalArgumentException("Patient id must be greater than 0");
        }
    }
}
