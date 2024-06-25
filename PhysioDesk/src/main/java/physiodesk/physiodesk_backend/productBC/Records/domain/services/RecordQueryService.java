package physiodesk.physiodesk_backend.productBC.Records.domain.services;

import physiodesk.physiodesk_backend.productBC.Records.domain.model.queries.GetAllRecordsQuery;
import physiodesk.physiodesk_backend.productBC.Records.domain.model.queries.GetRecordByFecha;
import physiodesk.physiodesk_backend.productBC.Records.domain.model.queries.GetRecordById;
import physiodesk.physiodesk_backend.productBC.Records.domain.model.aggregates.Records;

import java.util.List;
import java.util.Optional;

public interface RecordQueryService {

    List<Records> handle(GetAllRecordsQuery query);
    List<Records> handle(GetRecordByFecha query);
    Optional<Records> handle(GetRecordById query);
}
