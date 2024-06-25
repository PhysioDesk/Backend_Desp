package physiodesk.physiodesk_backend.productBC.products.domain.model.queries;

public record GetProductById(Long id) {

    public GetProductById {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
