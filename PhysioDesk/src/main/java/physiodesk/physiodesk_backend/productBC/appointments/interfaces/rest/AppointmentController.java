package physiodesk.physiodesk_backend.productBC.appointments.interfaces.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import physiodesk.physiodesk_backend.productBC.appointments.interfaces.rest.resources.AppointmentResource;
import physiodesk.physiodesk_backend.productBC.appointments.interfaces.rest.resources.CreateAppointmentResource;
import physiodesk.physiodesk_backend.productBC.appointments.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import physiodesk.physiodesk_backend.productBC.appointments.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.aggregates.Appointment;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAllAppointmentsByPatientIdQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAllAppointmentsByPhysiotherapistIdQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAllByPhysiotherapistIdAndDateQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.model.queries.GetAppointmentByIdQuery;
import physiodesk.physiodesk_backend.productBC.appointments.domain.services.IAppointmentCommandService;
import physiodesk.physiodesk_backend.productBC.appointments.domain.services.IAppointmentQueryService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@CrossOrigin(origins ="https://effulgent-lily-2a8d83.netlify.app/")
@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final IAppointmentCommandService appointmentCommandService;
    private final IAppointmentQueryService appointmentQueryService;


    public AppointmentController(IAppointmentCommandService appointmentCommandService, IAppointmentQueryService appointmentQueryService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody CreateAppointmentResource resource) {
        Optional<Appointment> appointment = appointmentCommandService.handle(CreateAppointmentCommandFromResourceAssembler.fromResource(resource));
        return appointment.map(source -> new ResponseEntity<>(AppointmentResourceFromEntityAssembler.fromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentQueryService.handle(new GetAppointmentByIdQuery(id));
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(NOT_FOUND).build());
    }

    @GetMapping("/physiotherapist/{physiotherapistId}")
    public ResponseEntity<List<AppointmentResource>> getAllAppointmentsByPhysiotherapistId(Long physiotherapistId) {
        var getAllAppointmentsByPhysiotherapistIdQuery = new GetAllAppointmentsByPhysiotherapistIdQuery(physiotherapistId);
        var appointments = appointmentQueryService.handle(getAllAppointmentsByPhysiotherapistIdQuery);
        if (appointments.isEmpty()) return ResponseEntity.notFound().build();
        var appointmentsResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::fromEntity).toList();
        return ResponseEntity.ok(appointmentsResources);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResource>> getAllAppointmentsByPatientId(Long patientId) {
        var getAllAppointmentsByPatientIdQuery = new GetAllAppointmentsByPatientIdQuery(patientId);
        var appointments = appointmentQueryService.handle(getAllAppointmentsByPatientIdQuery);
        if (appointments.isEmpty()) return ResponseEntity.notFound().build();
        var appointmentsResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::fromEntity).toList();
        return ResponseEntity.ok(appointmentsResources);
    }

    @GetMapping("/physiotherapist/{physiotherapistId}/date/{date}")
    public ResponseEntity<List<AppointmentResource>> getAllByPhysiotherapistIdAndDate(Long physiotherapistId, String date) {
        var getAllByPhysiotherapistIdAndDateQuery = new GetAllByPhysiotherapistIdAndDateQuery(physiotherapistId, date);
        var appointments = appointmentQueryService.handle(getAllByPhysiotherapistIdAndDateQuery);
        if (appointments.isEmpty()) return ResponseEntity.notFound().build();
        var appointmentsResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::fromEntity).toList();
        return ResponseEntity.ok(appointmentsResources);
    }

}
