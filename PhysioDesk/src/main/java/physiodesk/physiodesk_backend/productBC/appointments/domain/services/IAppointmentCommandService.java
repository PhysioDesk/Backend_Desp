package physiodesk.physiodesk_backend.productBC.appointments.domain.services;

import physiodesk.physiodesk_backend.productBC.appointments.domain.model.aggregates.Appointment;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.commands.CreateAppointmentCommand;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.commands.UpdateAppointmentCommand;

import java.util.Optional;

public interface IAppointmentCommandService {

    Optional<Appointment> handle (CreateAppointmentCommand command);
    //todo el profe aqui usa long handle para el create que fue???
    Optional<Appointment> handle(UpdateAppointmentCommand command);
}
