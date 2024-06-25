package physiodesk.physiodesk_backend.Users.domain.model.queries.user;

public record GetUserByPassword(String password) {

            public GetUserByPassword {
                if (password == null) {
                    throw new IllegalArgumentException("Password cannot be null");
                }
            }
}
