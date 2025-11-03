package supplychainx.springboot.production.service;

import supplychainx.springboot.production.dto.ProductRequestDto;
import supplychainx.springboot.production.dto.ProductResponseDto;
import supplychainx.springboot.production.model.Product;

import java.util.List;

public interface IProductService {
    Product create(ProductRequestDto productRequest);
    Product update(ProductRequestDto productRequest, Long id);
    Product findById(Long id);
    List<Product> findAllProducts();
    int delete(Long id);
    ProductResponseDto toResponse(Product product);
}
