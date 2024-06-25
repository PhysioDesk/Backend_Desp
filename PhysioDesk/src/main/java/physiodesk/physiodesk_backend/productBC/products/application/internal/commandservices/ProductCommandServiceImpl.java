package physiodesk.physiodesk_backend.productBC.products.application.internal.commandservices;

import org.springframework.stereotype.Service;
import physiodesk.physiodesk_backend.productBC.products.domain.model.aggregates.Product;
import physiodesk.physiodesk_backend.productBC.products.domain.model.commands.CreateProductCommand;
import physiodesk.physiodesk_backend.productBC.products.infrastructure.persistance.jpa.repositories.IProductRepository;
import physiodesk.physiodesk_backend.productBC.products.domain.services.IProductCommandService;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements IProductCommandService {

    private final IProductRepository productRepository;

    public ProductCommandServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command) {
        if (productRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Product already exists");
        }
        var product = new Product(command);
        var createdProduct = productRepository.save(product);
        return Optional.of(createdProduct);
    }

}
