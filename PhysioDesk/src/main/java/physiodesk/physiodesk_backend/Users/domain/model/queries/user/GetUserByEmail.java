package physiodesk.physiodesk_backend.Users.domain.model.queries.user;

public record GetUserByEmail(String email) {

        public GetUserByEmail {
            if (email == null) {
                throw new IllegalArgumentException("Email cannot be null");
            }
        }
}
