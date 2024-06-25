package physiodesk.physiodesk_backend.productBC.products.domain.services;

import physiodesk.physiodesk_backend.productBC.products.domain.model.aggregates.Product;
import physiodesk.physiodesk_backend.productBC.products.domain.model.commands.CreateProductCommand;

import java.util.Optional;

public interface IProductCommandService {

    Optional<Product> handle(CreateProductCommand command);

}
