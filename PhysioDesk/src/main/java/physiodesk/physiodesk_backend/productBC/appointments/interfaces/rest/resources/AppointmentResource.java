package physiodesk.physiodesk_backend.productBC.appointments.interfaces.rest.resources;

public record AppointmentResource (long id, long patientId, long physiotherapistId, String date, String time) {
}
