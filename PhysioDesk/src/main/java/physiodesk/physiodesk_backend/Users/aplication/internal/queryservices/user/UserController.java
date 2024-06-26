package physiodesk.physiodesk_backend.Users.aplication.internal.queryservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import physiodesk.physiodesk_backend.Security.JwtTokenProvider;
import physiodesk.physiodesk_backend.Users.domain.AuthResponse;
import physiodesk.physiodesk_backend.Users.domain.model.aggregates.user;
import physiodesk.physiodesk_backend.Users.domain.model.queries.user.GetAllUsersQuery;
import physiodesk.physiodesk_backend.Users.domain.model.queries.user.GetUserById;
import physiodesk.physiodesk_backend.Users.domain.services.user.userCommandService;
import physiodesk.physiodesk_backend.Users.domain.services.user.userQueryService;
import physiodesk.physiodesk_backend.Users.interfaces.rest.resources.user.CreateUserResource;
import physiodesk.physiodesk_backend.Users.interfaces.rest.resources.user.userResource;
import physiodesk.physiodesk_backend.Users.interfaces.rest.transform.user.CreateUserCommandFromResourceAssembler;
import physiodesk.physiodesk_backend.Users.interfaces.rest.transform.user.userResourceFromEntityAssembler;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final userQueryService userQueryService;
    private final userCommandService userCommandService;
    private final JwtTokenProvider jwtsTokenProvider;

    public UserController(userCommandService userCommandService, userQueryService userQueryService,JwtTokenProvider jwtsTokenProvider) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.jwtsTokenProvider = jwtsTokenProvider;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> createUser(@RequestBody CreateUserResource resource) {
        Optional<user> userOptional = userCommandService.handle(CreateUserCommandFromResourceAssembler.fromResource(resource));
        if (userOptional.isPresent()) {
            user newUser = userOptional.get();
            String token = jwtsTokenProvider.generateToken(newUser.getName()); // Generar el token JWT
            AuthResponse authResponse = new AuthResponse(token, newUser); // Crear un objeto de respuesta que incluya el token y los datos del usuario
            return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    /*@PostMapping
    public ResponseEntity<userResource> createUser(@RequestBody CreateUserResource resource) {
        Optional<user> user = userCommandService.handle(CreateUserCommandFromResourceAssembler.fromResource(resource));
        return user.map(source -> new ResponseEntity<>(userResourceFromEntityAssembler.fromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    */

    @GetMapping("/{id}")
    public ResponseEntity<userResource> getUserById(@PathVariable Long id) {
        Optional<user> user = userQueryService.handle(new GetUserById(id));
        return user.map(source -> ResponseEntity.ok(userResourceFromEntityAssembler.fromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<userResource>> getAllUsers() {
        var users = userQueryService.handle(new GetAllUsersQuery());
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResources = users.stream()
                .map(userResourceFromEntityAssembler::fromEntity)
                .toList();
        return ResponseEntity.ok(userResources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        boolean deleted = userCommandService.deleteUserById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<user> updateUserById(@PathVariable Long id, @RequestBody user userDetails) {
        return userCommandService.updateUserById(id, userDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<user> getUserByEmail(@PathVariable String email) {
        Optional<user> user = userQueryService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/password/{password}")
    public ResponseEntity<user> getUserByPassword(@PathVariable String password) {
        Optional<user> user = userQueryService.getUserByPassword(password);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}/password/{password}")
    public ResponseEntity<user> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        Optional<user> user = userQueryService.getUserByEmailAndPassword(email, password);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
