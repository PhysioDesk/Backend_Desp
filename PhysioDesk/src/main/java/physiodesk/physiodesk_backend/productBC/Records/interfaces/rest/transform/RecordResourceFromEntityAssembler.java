package physiodesk.physiodesk_backend.productBC.Records.interfaces.rest.transform;

import physiodesk.physiodesk_backend.productBC.Records.domain.model.aggregates.Records;
import physiodesk.physiodesk_backend.productBC.Records.interfaces.rest.resources.RecordResource;

public record RecordResourceFromEntityAssembler() {

    public static RecordResource fromEntity(Records entity) {
        return new RecordResource(entity.getId(), entity.getDate(), entity.getName(), entity.getDuration(), entity.getAccess());
    }
}
