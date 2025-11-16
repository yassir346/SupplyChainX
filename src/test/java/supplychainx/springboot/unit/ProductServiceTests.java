package supplychainx.springboot.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import supplychainx.springboot.production.dto.ProductRequestDto;
import supplychainx.springboot.production.model.Product;
import supplychainx.springboot.production.repository.ProductRepository;
import supplychainx.springboot.production.service.Impl.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllProductsTest(){
        List<Product> list = new ArrayList<Product>();
        Product product = new Product();
        product.setId(1L);
        product.setName("abc");
        product.setCost(10);
        product.setStock(1001);
        product.setProductionTime(24);

        Product product1 = new Product();
        product1.setId(2L);
        product1.setName("efg");
        product1.setCost(11);
        product1.setStock(100111);
        product1.setProductionTime(244);

        list.add(product);
        list.add(product1);

        when(productRepository.findAll()).thenReturn(list);

        List<Product> productList = productService.findAllProducts();

        Assertions.assertEquals(2, productList.size());
        verify(productRepository, times(1)).findAll();
     }

     @Test
    void testCreateProduct(){
         ProductRequestDto productRequest = new ProductRequestDto();
         productRequest.setName("newP");
         productRequest.setCost(10);
         productRequest.setStock(1001);
         productRequest.setProductionTime(24);

         Product product = new Product();
         product.setId(3L);
         product.setName("abc");
         product.setCost(10);
         product.setStock(1001);
         product.setProductionTime(24);

         when(productRepository.save(product)).thenReturn(product);
         productService.create(productRequest);
         verify(productRepository, times(1)).save(product);
     }
}
