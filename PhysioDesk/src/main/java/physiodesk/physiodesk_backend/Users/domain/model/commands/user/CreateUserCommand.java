package physiodesk.physiodesk_backend.Users.domain.model.commands.user;

public record CreateUserCommand(String name, String lastname,Long phone,String email, String address, String city, String password) {

    public CreateUserCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("UserCode cannot be null or empty");
        }
        if (phone < 0) {
            throw new IllegalArgumentException("Phone must be greater than or equal to 0");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }
}
