package supplychainx.springboot.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supplychainx.springboot.production.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
