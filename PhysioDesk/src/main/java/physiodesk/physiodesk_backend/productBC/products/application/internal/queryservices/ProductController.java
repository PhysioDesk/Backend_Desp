package physiodesk.physiodesk_backend.productBC.products.application.internal.queryservices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import physiodesk.physiodesk_backend.productBC.products.domain.model.aggregates.Product;
import physiodesk.physiodesk_backend.productBC.products.domain.model.queries.GetAllQuery;
import physiodesk.physiodesk_backend.productBC.products.domain.model.queries.GetProductById;
import physiodesk.physiodesk_backend.productBC.products.domain.services.IProductCommandService;
import physiodesk.physiodesk_backend.productBC.products.domain.services.IProductQueryService;
import physiodesk.physiodesk_backend.productBC.products.interfaces.rest.resources.CreateProductResource;
import physiodesk.physiodesk_backend.productBC.products.interfaces.rest.resources.ProductResource;
import physiodesk.physiodesk_backend.productBC.products.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import physiodesk.physiodesk_backend.productBC.products.interfaces.rest.transform.ProductResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins ="https://effulgent-lily-2a8d83.netlify.app/")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final IProductQueryService productQueryService;
    private final IProductCommandService productCommandService;

    public ProductController(IProductQueryService productQueryService, IProductCommandService productCommandService) {
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
    }

    @PostMapping

    public ResponseEntity<ProductResource> createFavoriteSource(@RequestBody CreateProductResource resource) {

        Optional<Product> product = productCommandService.handle(CreateProductCommandFromResourceAssembler.fromResource(resource));

        return product.map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.fromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long id) {
        Optional<Product> product = productQueryService.handle(new GetProductById(id));
        return product.map(source -> ResponseEntity.ok(ProductResourceFromEntityAssembler.fromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        var products = productQueryService.handle(new GetAllQuery());
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var productResources = products.stream().map(ProductResourceFromEntityAssembler::fromEntity).toList();
        return ResponseEntity.ok(productResources);
    }




}
