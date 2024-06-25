package physiodesk.physiodesk_backend.productBC.Records.domain.model.commands;

public record CreateRecordCommand (long id,String date,String name,String duration,String access){

    public CreateRecordCommand{
        if(date == null || date.isBlank()){
            throw new IllegalArgumentException("date is required");
        }
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name is required");
        }
        if(duration == null || duration.isBlank()){
            throw new IllegalArgumentException("duration is required");
        }
        if(access == null || access.isBlank()){
            throw new IllegalArgumentException("access is required");
        }
    }
}
