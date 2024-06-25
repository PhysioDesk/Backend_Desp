package physiodesk.physiodesk_backend.productBC.Records.domain.model.queries;

public record GetRecordByFecha(String fecha) {

            public GetRecordByFecha {
                if (fecha == null) {
                    throw new IllegalArgumentException("Fecha cannot be null");
                }
            }
}
