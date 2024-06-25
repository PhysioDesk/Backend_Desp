package physiodesk.physiodesk_backend.productBC.appointments.interfaces.rest.transform;

import physiodesk.physiodesk_backend.productBC.appointments.interfaces.rest.resources.AppointmentResource;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.aggregates.Appointment;

public class AppointmentResourceFromEntityAssembler {

    public static AppointmentResource fromEntity(Appointment appointment) {
        return new AppointmentResource(appointment.getId(), appointment.getPatientId(), appointment.getPhysiotherapistId(), appointment.getDate(), appointment.getTime());
    }
}
