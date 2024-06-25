package physiodesk.physiodesk_backend.Users.aplication.internal.queryservices.physio;

import org.springframework.stereotype.Service;
import physiodesk.physiodesk_backend.Users.domain.model.aggregates.physio;
import physiodesk.physiodesk_backend.Users.domain.model.queries.physio.GetAllPhysiosQuery;
import physiodesk.physiodesk_backend.Users.domain.model.queries.physio.GetPhysioById;
import physiodesk.physiodesk_backend.Users.domain.services.physio.physioQueryService;
import physiodesk.physiodesk_backend.Users.infraestructure.persistance.jpa.physio.physioRepository;
import java.util.List;
import java.util.Optional;

@Service
public class physioQueryServiceImpl implements physioQueryService {

    public static physioRepository physioRepository;
    public physioQueryServiceImpl(physioRepository physioRepository) {
        this.physioRepository = physioRepository;
    }

    @Override
    public List<physio> handle(GetAllPhysiosQuery query){
        return physioRepository.findAll();
    }

    @Override
    public Optional<physio> handle(GetPhysioById query){
        return physioRepository.findById(query.id());
    }
}
