package physiodesk.physiodesk_backend.Users.domain.model.queries.user;

public record GetUserByEmailAndPassword(String email, String password) {

            public GetUserByEmailAndPassword {
                if (email == null) {
                    throw new IllegalArgumentException("Email cannot be null");
                }
                if (password == null) {
                    throw new IllegalArgumentException("Password cannot be null");
                }
            }
}
