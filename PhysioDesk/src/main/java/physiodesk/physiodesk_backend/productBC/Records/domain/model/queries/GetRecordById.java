package physiodesk.physiodesk_backend.productBC.Records.domain.model.queries;

public record GetRecordById(Long id) {

        public GetRecordById {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null");
            }
        }
}
