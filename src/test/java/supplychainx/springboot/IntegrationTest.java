package supplychainx.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import supplychainx.springboot.production.controller.ProductController;
import supplychainx.springboot.production.dto.ProductRequestDto;
import supplychainx.springboot.production.dto.ProductResponseDto;
import supplychainx.springboot.production.model.Product;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTest {
    @Autowired
    ProductController productController;

    @Test
    public void testCreateReadDelete(){
        ProductRequestDto product = new ProductRequestDto();
        product.setName("abc");
        product.setCost(10);
        product.setStock(1001);
        product.setProductionTime(24);

        ResponseEntity productResult = productController.create(product);

        ResponseEntity<ProductResponseDto> products = productController.create(product);

    }
}
