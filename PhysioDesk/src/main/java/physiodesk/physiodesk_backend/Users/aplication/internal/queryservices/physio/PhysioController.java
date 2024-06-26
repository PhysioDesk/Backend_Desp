package physiodesk.physiodesk_backend.Users.aplication.internal.queryservices.physio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import physiodesk.physiodesk_backend.Security.JwtTokenProvider;
import physiodesk.physiodesk_backend.Users.domain.AuthResponse;
import physiodesk.physiodesk_backend.Users.domain.model.aggregates.physio;
import physiodesk.physiodesk_backend.Users.domain.model.queries.physio.GetAllPhysiosQuery;
import physiodesk.physiodesk_backend.Users.domain.model.queries.physio.GetPhysioById;
import physiodesk.physiodesk_backend.Users.domain.services.physio.physioCommandService;
import physiodesk.physiodesk_backend.Users.domain.services.physio.physioQueryService;
import physiodesk.physiodesk_backend.Users.interfaces.rest.resources.physio.CreatePhysioResource;
import physiodesk.physiodesk_backend.Users.interfaces.rest.resources.physio.physioResource;
import physiodesk.physiodesk_backend.Users.interfaces.rest.transform.physio.CreatePhysioCommandFromResourceAssembler;
import physiodesk.physiodesk_backend.Users.interfaces.rest.transform.physio.physioResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ="https://effulgent-lily-2a8d83.netlify.app/")
@RestController
@RequestMapping("/api/v1/physios")
public class PhysioController {

    private final physioQueryService physioQueryService;
    private final physioCommandService physioCommandService;
    private final JwtTokenProvider jwtsTokenProvider;

    public PhysioController(physioCommandService physioCommandService, physioQueryService physioQueryService,JwtTokenProvider jwtsTokenProvider) {
        this.physioCommandService = physioCommandService;
        this.physioQueryService = physioQueryService;
        this.jwtsTokenProvider = jwtsTokenProvider;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> createPhysio(@RequestBody CreatePhysioResource resource) {
        Optional<physio> physioOptional = physioCommandService.handle(CreatePhysioCommandFromResourceAssembler.fromResource(resource));
        if (physioOptional.isPresent()) {
            physio newPhysio = physioOptional.get();
            String token = jwtsTokenProvider.generateToken(newPhysio.getNombre()); // Generar el token JWT
            AuthResponse authResponse = new AuthResponse(token, newPhysio); // Crear un objeto de respuesta que incluya el token y los datos del fisioterapeuta
            return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<physio> getPhysioById(@PathVariable Long id) {
        Optional<physio> physio = physioQueryService.handle(new GetPhysioById(id));
        return physio.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<physioResource>> getAllPhysios() {
        var physios = physioQueryService.handle(new GetAllPhysiosQuery());
        if (physios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var physioResources = physios.stream()
                    .map(physioResourceFromEntityAssembler::fromEntity)
                    .toList();
        return ResponseEntity.ok(physioResources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhysioById(@PathVariable Long id) {
        boolean deleted = physioCommandService.deletePhysioById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<physio> updatePhysioById(@PathVariable Long id, @RequestBody physio physioDetails) {
        return physioCommandService.updatePhysioById(id, physioDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
