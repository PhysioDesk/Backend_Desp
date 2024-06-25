package physiodesk.physiodesk_backend.Users.domain.model.queries.physio;

public record GetPhysioById(Long id) {

    public GetPhysioById {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}