package physiodesk.physiodesk_backend.productBC.appointments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.aggregates.Appointment;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAllAppointmentsByPatientIdQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAllAppointmentsByPhysiotherapistIdQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAllByPhysiotherapistIdAndDateQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAppointmentByIdQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.services.IAppointmentQueryService;
import physiodesk.physiodesk_backend.productBC.appointments.infrastructure.persistance.jpa.repositories.IAppointmentRepository;

import java.util.List;
import java.util.Optional;
@Service
public class AppointmentQueryServiceImpl implements IAppointmentQueryService {

    public static IAppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByPhysiotherapistIdQuery query) {
        return appointmentRepository.findAllByPhysiotherapistId(query.physiotherapistId());
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.id());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByPatientIdQuery query) {
        return appointmentRepository.findAllByPatientId(query.patientId());
    }

    @Override
    public List<Appointment> handle(GetAllByPhysiotherapistIdAndDateQuery query) {
        return appointmentRepository.findAllByPhysiotherapistIdAndDate(query.physiotherapistId(), query.date());
    }
}
