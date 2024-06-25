package physiodesk.physiodesk_backend.productBC.products.interfaces.rest.transform;

import physiodesk.physiodesk_backend.productBC.products.domain.model.aggregates.Product;
import physiodesk.physiodesk_backend.productBC.products.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource fromEntity(Product entity) {
        return new ProductResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getImageUrl(), entity.getRating(), entity.getDimensiones());
    }
}
