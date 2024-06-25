package physiodesk.physiodesk_backend.productBC.appointments.domain.model.commands;

public record UpdateAppointmentCommand(Long id, String date, String time) {
}
