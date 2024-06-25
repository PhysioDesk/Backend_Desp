package physiodesk.physiodesk_backend.Users.aplication.internal.commandservices.physio;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import physiodesk.physiodesk_backend.Users.domain.model.aggregates.physio;
import physiodesk.physiodesk_backend.Users.domain.model.commands.physio.CreatePhysioCommand;
import physiodesk.physiodesk_backend.Users.domain.services.physio.physioCommandService;
import physiodesk.physiodesk_backend.Users.infraestructure.persistance.jpa.physio.physioRepository;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class physioCommandServiceImpl implements physioCommandService {
    private final physioRepository PhysioRepository;
    private final Key jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long jwtExpirationMs = 86400000; // 24 horas

    public physioCommandServiceImpl(physioRepository PhysioRepository) {
        this.PhysioRepository = PhysioRepository;
    }

    @Override
    public Optional<physio> handle(CreatePhysioCommand command) {
        if (PhysioRepository.existsById(command.id())) {
            throw new IllegalArgumentException("physio already exists");
        }
        var physio = new physio(command);
        var createdphysio = PhysioRepository.save(physio);

        String jwtToken = generateJwtToken(physio);
        return Optional.of(createdphysio);
    }

    private String generateJwtToken(physio physio) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(Long.toString(physio.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(jwtSecretKey)
                .compact();
    }

    @Override
    public boolean deletePhysioById(Long id) {
        if (!PhysioRepository.existsById(id)) {
            return false; // El fisioterapeuta con el ID dado no existe, por lo que no se puede eliminar
        }
        PhysioRepository.deleteById(id);
        return true; // Eliminación exitosa
    }

    @Override
    public Optional<physio> updatePhysioById(Long id, physio updatedPhysio) {
        Optional<physio> optionalPhysio = PhysioRepository.findById(id);
        if (optionalPhysio.isPresent()) {
            physio existingPhysio = optionalPhysio.get();
            existingPhysio.setNombre(updatedPhysio.getNombre());
            existingPhysio.setImagen(updatedPhysio.getImagen());
            existingPhysio.setBiografia(updatedPhysio.getBiografia());
            existingPhysio.setEdad(updatedPhysio.getEdad());
            existingPhysio.setHorario(updatedPhysio.getHorario());
            return Optional.of(PhysioRepository.save(existingPhysio));
        }
        return Optional.empty();
    }
}
