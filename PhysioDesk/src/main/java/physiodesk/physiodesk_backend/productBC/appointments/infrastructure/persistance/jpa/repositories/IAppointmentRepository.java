package physiodesk.physiodesk_backend.productBC.appointments.infrastructure.persistance.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.aggregates.Appointment;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    //obtener todos los appointments de un paciente
    List<Appointment> findAllByPatientId(Long patientId);

    //obtener todos los appointments de un fisioterapeuta
    List<Appointment> findAllByPhysiotherapistId(Long physiotherapistId);

    //obtener todos los appointments de un fisio en una fecha
    List<Appointment> findAllByPhysiotherapistIdAndDate(Long physiotherapistId, String date);

    Boolean existsByPatientIdAndPhysiotherapistIdAndDate(Long patientId, Long physiotherapistId, String date);
}
