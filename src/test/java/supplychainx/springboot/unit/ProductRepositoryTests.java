package supplychainx.springboot.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import supplychainx.springboot.production.model.Product;
import supplychainx.springboot.production.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testCreateReadDelete(){
        Product product = new Product();
        product.setName("abc");
        product.setCost(10);
        product.setStock(1001);
        product.setProductionTime(24);

        productRepository.save(product);

        Iterable<Product> products = productRepository.findAll();
        Assertions.assertThat(products).extracting(Product::getName).containsOnlyOnce("abc");

        productRepository.deleteById(11L);
        Assertions.assertThat(productRepository.findAll().isEmpty());
    }
}
