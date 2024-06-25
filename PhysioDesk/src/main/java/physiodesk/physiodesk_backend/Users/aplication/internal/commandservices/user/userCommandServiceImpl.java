package physiodesk.physiodesk_backend.Users.aplication.internal.commandservices.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import physiodesk.physiodesk_backend.Users.domain.model.aggregates.physio;
import physiodesk.physiodesk_backend.Users.domain.model.aggregates.user;
import physiodesk.physiodesk_backend.Users.domain.model.commands.user.CreateUserCommand;
import physiodesk.physiodesk_backend.Users.domain.services.user.userCommandService;
import physiodesk.physiodesk_backend.Users.infraestructure.persistance.jpa.user.userRepository;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class userCommandServiceImpl implements userCommandService {
    private final userRepository UserRepository;
    private final Key jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long jwtExpirationMs = 86400000; // 24 horas

    public userCommandServiceImpl(userRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    @Override
    public Optional<user> handle(CreateUserCommand command) {
        var user = new user(command);
        var createduser = UserRepository.save(user);

        String jwtToken = generateJwtToken(user);
        return Optional.of(createduser);
    }

    private String generateJwtToken(user user) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(jwtSecretKey)
                .compact();
    }

    @Override
    public boolean deleteUserById(Long id) {
        if (!UserRepository.existsById(id)) {
            return false; // El usuario con el ID dado no existe, por lo que no se puede eliminar
        }
        UserRepository.deleteById(id);
        return true; // Eliminación exitosa
    }

    @Override
    public Optional<user> updateUserById(Long id, user updatedUser) {
        Optional<user> optionalUser = UserRepository.findById(id);
        if (optionalUser.isPresent()) {
            user existingUser = optionalUser.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setLastname(updatedUser.getLastname());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setCity(updatedUser.getCity());

            return Optional.of(UserRepository.save(existingUser));
        }
        return Optional.empty();
    }
}
