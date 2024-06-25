package physiodesk.physiodesk_backend.productBC.products.domain.model.aggregates;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import physiodesk.physiodesk_backend.productBC.products.domain.model.commands.CreateProductCommand;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product extends AbstractAggregateRoot<Product> {
    //abstract aggregate root is a class that is used to represent an aggregate root in the domain model
    //un aggregate root is an entity that is used to represent a group of objects that are treated as a single unit



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private String description;

    @Column(nullable = false)
    @Getter
    private double price;

    @Column(nullable = false)
    @Getter
    private String imageUrl;

    @Column(nullable = false)
    @Getter
    private String rating;

    @Getter
    private String dimensiones;

    protected Product() {
    }

    public Product(Long id, String name, String description, double price, String imageUrl, String rating, String dimensiones) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.dimensiones = dimensiones;
    }

    public Product(CreateProductCommand command){
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();
        this.imageUrl = command.imageUrl();
        this.rating = command.rating();
        this.dimensiones = command.dimensiones();

    }

}
