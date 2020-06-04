package ShoppingCart.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ShoppingCart.Product.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
}
