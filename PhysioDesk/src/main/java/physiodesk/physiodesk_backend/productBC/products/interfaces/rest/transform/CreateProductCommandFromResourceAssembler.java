package physiodesk.physiodesk_backend.productBC.products.interfaces.rest.transform;

import physiodesk.physiodesk_backend.productBC.products.domain.model.commands.CreateProductCommand;
import physiodesk.physiodesk_backend.productBC.products.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand fromResource(CreateProductResource resource) {
        return new CreateProductCommand(resource.id(), resource.name(), resource.description(), resource.price(), resource.imageUrl(), resource.rating(), resource.dimensiones());
    }
}
