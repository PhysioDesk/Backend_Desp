package physiodesk.physiodesk_backend.productBC.appointments.domain.services;

import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAllAppointmentsByPhysiotherapistIdQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAllByPhysiotherapistIdAndDateQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAppointmentByIdQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.aggregates.Appointment;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAllAppointmentsByPatientIdQuery;

import java.util.List;
import java.util.Optional;

public interface IAppointmentQueryService {

    List<Appointment> handle(GetAllAppointmentsByPhysiotherapistIdQuery query);

    Optional<Appointment> handle(GetAppointmentByIdQuery query);

    List<Appointment> handle(GetAllAppointmentsByPatientIdQuery query);

    List<Appointment> handle(GetAllByPhysiotherapistIdAndDateQuery query);
}
