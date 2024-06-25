package physiodesk.physiodesk_backend.productBC.products.infrastructure.persistance.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import physiodesk.physiodesk_backend.productBC.products.domain.model.aggregates.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Optional<Product> GetProductById(@Param("id") Long id);
    @Query("SELECT p FROM Product p")
    List<Product> GetAll();
}
