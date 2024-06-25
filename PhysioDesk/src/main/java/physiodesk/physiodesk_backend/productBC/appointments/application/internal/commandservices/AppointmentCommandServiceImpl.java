package physiodesk.physiodesk_backend.productBC.appointments.application.internal.commandservices;

import org.springframework.stereotype.Service;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.aggregates.Appointment;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.commands.CreateAppointmentCommand;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.commands.UpdateAppointmentCommand;
import physiodesk.physiodesk_backend.productBC.appointments.domain.services.IAppointmentCommandService;
import physiodesk.physiodesk_backend.productBC.appointments.infrastructure.persistance.jpa.repositories.IAppointmentRepository;

import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements IAppointmentCommandService {

    private final IAppointmentRepository appointmentRepository;

    public AppointmentCommandServiceImpl(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> handle(CreateAppointmentCommand command) {
        if(appointmentRepository.existsByPatientIdAndPhysiotherapistIdAndDate(command.patientId(), command.physiotherapistId(), command.date())){
            throw new IllegalArgumentException("Appointment already exists");
        }
        var appointment = new Appointment(command);
        var savedAppointment = appointmentRepository.save(appointment);
        return Optional.of(savedAppointment);
    }

    @Override
    public Optional<Appointment> handle(UpdateAppointmentCommand command) {
        if (appointmentRepository.existsById(command.id()))
            throw new IllegalArgumentException("Appointment with same details already exists");
        var result = appointmentRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Appointment does not exist");
        var appointmentToUpdate = result.get();
        try {
            var updatedAppointment = appointmentRepository.save(appointmentToUpdate.UpdateInformation(command.date(), command.time()));
            return Optional.of(updatedAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating appointment: " + e.getMessage());
        }
    }
}
