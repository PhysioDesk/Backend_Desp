package physiodesk.physiodesk_backend.Users.domain.services.physio;

import physiodesk.physiodesk_backend.Users.domain.model.aggregates.physio;
import physiodesk.physiodesk_backend.Users.domain.model.queries.physio.GetAllPhysiosQuery;
import physiodesk.physiodesk_backend.Users.domain.model.queries.physio.GetPhysioById;

import java.util.List;
import java.util.Optional;

public interface physioQueryService {

    List<physio> handle(GetAllPhysiosQuery query);
    Optional<physio> handle(GetPhysioById query);
}
