package physiodesk.physiodesk_backend.productBC.Records.interfaces.rest.resources;

public record CreateRecordResource(long id,String date, String name,String duration,String access){

public CreateRecordResource {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be greater than or equal to 0");
        }
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (duration == null || duration.isBlank()) {
            throw new IllegalArgumentException("Duration cannot be null or empty");
        }
        if (access == null || access.isBlank()) {
            throw new IllegalArgumentException("Access cannot be null or empty");
        }
    }

}
