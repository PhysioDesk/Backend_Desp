package physiodesk.physiodesk_backend.productBC.appointments.domain.model.commands;

public record CreateAppointmentCommand(long patientId, long physiotherapistId, String date, String time){
    public CreateAppointmentCommand{
        if(date == null || date.isBlank()){
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        if(time == null || time.isBlank()){
            throw new IllegalArgumentException("Time cannot be null or empty");
        }

    }
}
