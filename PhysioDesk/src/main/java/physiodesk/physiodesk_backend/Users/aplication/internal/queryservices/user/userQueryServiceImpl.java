package physiodesk.physiodesk_backend.Users.aplication.internal.queryservices.user;

import org.springframework.stereotype.Service;
import physiodesk.physiodesk_backend.Users.domain.model.aggregates.user;
import physiodesk.physiodesk_backend.Users.domain.model.queries.user.GetAllUsersQuery;
import physiodesk.physiodesk_backend.Users.domain.model.queries.user.GetUserById;
import physiodesk.physiodesk_backend.Users.domain.services.user.userQueryService;
import physiodesk.physiodesk_backend.Users.infraestructure.persistance.jpa.user.userRepository;
import java.util.List;
import java.util.Optional;

@Service
public class userQueryServiceImpl implements userQueryService {

    public static userRepository userRepository;
    public userQueryServiceImpl(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<user> handle(GetAllUsersQuery query){
        return userRepository.findAll();
    }

    @Override
    public Optional<user> handle(GetUserById query){
        return userRepository.findById(query.id());
}

    @Override
    public Optional<user> getUserByEmail(String email){
        return userRepository.GetUserByEmail(email);
    }

    @Override
    public Optional<user> getUserByPassword(String password){
        return userRepository.GetUserByPassword(password);
    }

    @Override
    public Optional<user> getUserByEmailAndPassword(String email, String password){
        return userRepository.GetUserByEmailAndPassword(email, password);
    }

}
