package supplychainx.springboot.unit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import supplychainx.springboot.production.controller.ProductController;
import supplychainx.springboot.production.model.Product;
import supplychainx.springboot.production.service.IProductService;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {
    @MockitoBean
    IProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAllProducts(){
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

        List<Product> products = Arrays.asList(product, product1);

        Mockito.when(productService.findAllProducts()).thenReturn(products);
        try {
            mockMvc.perform(get("/product/"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
